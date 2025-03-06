package eu.senla.financialtransactions.service.impl;

import eu.senla.financialtransactions.dto.MessageResponseDto;
import eu.senla.financialtransactions.dto.PaymentRequestDto;
import eu.senla.financialtransactions.dto.PaymentRequestMessageDto;
import eu.senla.financialtransactions.dto.UuidDto;
import eu.senla.financialtransactions.entity.Client;
import eu.senla.financialtransactions.entity.Payment;
import eu.senla.financialtransactions.exception.ApplicationException;
import eu.senla.financialtransactions.mapper.PaymentMapper;
import eu.senla.financialtransactions.repository.ClientRepository;
import eu.senla.financialtransactions.repository.PaymentRepository;
import eu.senla.financialtransactions.service.CardService;
import eu.senla.financialtransactions.service.PaymentService;
import eu.senla.financialtransactions.util.OperationDataSetter;
import eu.senla.financialtransactions.util.ResponseOperationHandler;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static eu.senla.financialtransactions.exception.ApplicationError.CLIENT_NOT_FOUND;
import static eu.senla.financialtransactions.exception.ApplicationError.TRANSFER_NOT_FOUND;
import static eu.senla.financialtransactions.util.OperationDataValidator.validateDataForCheck;
import static eu.senla.financialtransactions.util.OperationDataValidator.validateDataForExecute;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final CardService cardService;

    private final ClientRepository clientRepository;

    private final PaymentRepository paymentRepository;

    private final PaymentMapper paymentMapper;

    private final ResponseOperationHandler responseOperationHandler;

    @NotNull
    @Override
    public UuidDto checkPayment(@NotNull PaymentRequestDto paymentRequestDto) {
        final Client client = clientRepository.findById(paymentRequestDto.getClientId())
                .orElseThrow(() -> CLIENT_NOT_FOUND.withParams(paymentRequestDto.getClientId()));
        final Payment payment = paymentMapper.toPayment(paymentRequestDto);
        final MessageResponseDto messageResponseDto =
                cardService.getClientCard(payment.getClient().getId());
        validateDataForCheck(payment, messageResponseDto);
        OperationDataSetter.setDataAfterCheck(payment, client);
        paymentRepository.save(payment);
        return paymentMapper.toUuidDto(payment);
    }

    @Transactional
    @NotNull
    @Override
    public MessageResponseDto executePayment(@NotNull UuidDto uuidDto) {
        final Payment payment = paymentRepository.findById(uuidDto.getId()).orElseThrow(
                () -> new ApplicationException(TRANSFER_NOT_FOUND));
        final MessageResponseDto messageResponseDto =
                cardService.getClientCard(payment.getClient().getId());
        validateDataForExecute(payment, messageResponseDto);
        final PaymentRequestMessageDto paymentRequestDto =
                paymentMapper.toPaymentMessageRequestDto(payment);
        final MessageResponseDto messageDtoAfterExecute =
                cardService.executeWithdrawalOfMoney(paymentRequestDto);
        return responseOperationHandler.saveData(messageDtoAfterExecute, payment);
    }
}
