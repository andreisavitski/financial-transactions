package eu.senla.financialtransactions.service.impl;

import eu.senla.financialtransactions.dto.*;
import eu.senla.financialtransactions.entity.Client;
import eu.senla.financialtransactions.entity.Payment;
import eu.senla.financialtransactions.exception.ApplicationException;
import eu.senla.financialtransactions.mapper.PaymentMapper;
import eu.senla.financialtransactions.repository.ClientRepository;
import eu.senla.financialtransactions.repository.PaymentRepository;
import eu.senla.financialtransactions.service.CardService;
import eu.senla.financialtransactions.service.PaymentService;
import eu.senla.financialtransactions.service.rabbitmq.RabbitMqMessagePaymentSender;
import eu.senla.financialtransactions.util.MessageConverter;
import eu.senla.financialtransactions.util.OperationDataSetter;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static eu.senla.financialtransactions.constant.AppStatusConstant.OK;
import static eu.senla.financialtransactions.exception.ApplicationError.CLIENT_NOT_FOUND;
import static eu.senla.financialtransactions.exception.ApplicationError.TRANSFER_NOT_FOUND;
import static eu.senla.financialtransactions.util.OperationDataSetter.setDataAfterExecute;
import static eu.senla.financialtransactions.util.OperationDataValidator.validateDataForCheck;
import static eu.senla.financialtransactions.util.OperationDataValidator.validateDataForExecute;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final CardService cardService;

    private final ClientRepository clientRepository;

    private final PaymentRepository paymentRepository;

    private final PaymentMapper paymentMapper;

    private final RabbitMqMessagePaymentSender paymentSender;

    @NotNull
    @Override
    public UuidDto checkPayment(@NotNull PaymentRequestDto paymentRequestDto) {
        final Client client = clientRepository.findById(paymentRequestDto.getClientId())
                .orElseThrow(() -> new ApplicationException(CLIENT_NOT_FOUND));
        final Payment payment = paymentMapper.toPayment(paymentRequestDto);
        final MessageResponseDto messageResponseDto =
                cardService.getClientCard(payment.getClient().getId());
        validateDataForCheck(payment, messageResponseDto);
        final Payment paymentAfterSet =
                (Payment) OperationDataSetter.setDataAfterCheck(payment, client);
        paymentRepository.save(paymentAfterSet);
        return paymentMapper.toUuidDto(paymentAfterSet);
    }

    @Transactional
    @NotNull
    @Override
    public MessageResponseDto executePayment(@NotNull UuidDto uuidDto) {
        final Payment payment = paymentRepository.findById(uuidDto.getId())
                .orElseThrow(() -> new ApplicationException(TRANSFER_NOT_FOUND));
        final MessageResponseDto messageResponseDto =
                cardService.getClientCard(payment.getClient().getId());
        validateDataForExecute(payment, messageResponseDto);
        final PaymentRequestMessageDto paymentRequestDto = paymentMapper.toPaymentMessageRequestDto(payment);
        final MessageResponseDto messageResponseDtoAfterExecute =
                cardService.executeWithdrawalOfMoney(paymentRequestDto);
        if (messageResponseDtoAfterExecute.getStatus().equals(OK)) {
            final Message message =
                    paymentSender.sendMoneyToOperator(buildOperatorRequestDto(payment));
            final MessageResponseDto messageResponseDtoFromOperatorService
                    = MessageConverter.convertToObj(message.getBody(), MessageResponseDto.class);
            if (messageResponseDtoFromOperatorService.getStatus().equals(OK)) {
                Payment paymentAfterExecute = (Payment) setDataAfterExecute(payment);
                paymentRepository.save(paymentAfterExecute);
            }
        }
        return messageResponseDtoAfterExecute;
    }

    @NotNull
    private OperatorRequestDto buildOperatorRequestDto(@NotNull Payment payment) {
        return OperatorRequestDto.builder()
                .amount(payment.getAmount())
                .operatorId(payment.getOperatorId())
                .clientId(payment.getClient().getId())
                .build();
    }
}