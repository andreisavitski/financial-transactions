package eu.senla.financialtransactions.service;

import eu.senla.financialtransactions.dto.MessageResponseDto;
import eu.senla.financialtransactions.dto.PaymentRequestMessageDto;
import eu.senla.financialtransactions.dto.TransferRequestDto;
import jakarta.validation.constraints.NotNull;

public interface CardService {

    MessageResponseDto getClientCard(@NotNull Long id);

    @NotNull
    MessageResponseDto executeTransferMoney(@NotNull TransferRequestDto transferRequestDto);

    @NotNull
    MessageResponseDto executeWithdrawalOfMoney(
            @NotNull PaymentRequestMessageDto paymentRequestMessageDto);
}
