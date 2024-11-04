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
import eu.senla.financialtransactions.util.CardExtractor;
import eu.senla.financialtransactions.util.MessageConverter;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static eu.senla.financialtransactions.constant.AppStatusConstant.OK;
import static eu.senla.financialtransactions.enums.Status.DONE;
import static eu.senla.financialtransactions.enums.Status.IN_PROGRESS;
import static eu.senla.financialtransactions.exception.ApplicationError.*;

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
        final Client client = clientRepository.findById(paymentRequestDto.getClientId()).orElseThrow(
                () -> new ApplicationException(CLIENT_NOT_FOUND)
        );
        final Payment payment = paymentMapper.toPayment(paymentRequestDto);
        validateDataForCheck(payment);
        setDataToNewPayment(payment, client);
        paymentRepository.save(payment);
        return paymentMapper.toUuidDto(payment);
    }

    @NotNull
    @Override
    public MessageResponseDto executePayment(@NotNull UuidDto uuidDto) {
        final Payment payment = paymentRepository.findById(uuidDto.getId()).orElseThrow(
                () -> new ApplicationException(TRANSFER_NOT_FOUND)
        );
        validateDataForExecute(payment);
        final PaymentRequestDto paymentRequestDto = paymentMapper.toPaymentRequestDto(payment);
        final MessageResponseDto messageResponseDto =
                cardService.executeWithdrawalOfMoney(paymentRequestDto);
        if (messageResponseDto.getStatus().equals(OK)) {
            final Message message = paymentSender.sendMoneyToOperator(OperatorRequestDto.builder()
                    .amount(payment.getAmount())
                    .operatorId(payment.getOperatorId())
                    .clientId(payment.getClient().getId())
                    .build());
            final MessageResponseDto messageResponseDtoFromOperatorService
                    = MessageConverter.convertToObj(message.getBody(), MessageResponseDto.class);
            if (messageResponseDtoFromOperatorService.getStatus().equals(OK)) {
                payment.setPaymentEndDateTime(LocalDateTime.now());
                payment.setStatus(DONE);
                paymentRepository.save(payment);
            }
        }
        return messageResponseDto;
    }

    private void validateDataForCheck(@NotNull Payment payment) {
        final MessageResponseDto messageResponseDto = cardService.getClientCard(payment.getClient().getId());
        final List<CardDto> card = messageResponseDto.getData();
        final CardDto cardDto = CardExtractor.getCardFromListByCardId(card, payment.getCardId());
        if (cardDto.getAmount().compareTo(payment.getAmount()) < 0) {
            throw new ApplicationException(NOT_ENOUGH_FUNDS);
        }
    }

    private void validateDataForExecute(@NotNull Payment payment) {
        if (payment.getStatus().equals(DONE)) {
            throw new ApplicationException(ALREADY_COMPLETED);
        }
        validateDataForCheck(payment);
    }

    private void setDataToNewPayment(@NotNull Payment payment, @NotNull Client client) {
        payment.setClient(client);
        payment.setId(UUID.randomUUID());
        payment.setStatus(IN_PROGRESS);
        payment.setPaymentStartDateTime(LocalDateTime.now());
    }
}
