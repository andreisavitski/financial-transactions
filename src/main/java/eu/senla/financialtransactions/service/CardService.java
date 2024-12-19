package eu.senla.financialtransactions.service;

import eu.senla.financialtransactions.dto.ClientCardRequestDto;
import eu.senla.financialtransactions.dto.MessageResponseDto;
import eu.senla.financialtransactions.dto.PaymentRequestMessageDto;
import eu.senla.financialtransactions.dto.TransferRequestDto;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public interface CardService {

    MessageResponseDto getClientCard(@NotNull UUID id);

    @NotNull
    MessageResponseDto executeTransferMoney(@NotNull TransferRequestDto transferRequestDto);

    @NotNull
    MessageResponseDto executeWithdrawalOfMoney(
            @NotNull PaymentRequestMessageDto paymentRequestMessageDto);

    @NotNull
    MessageResponseDto addCard(@NotNull ClientCardRequestDto clientCardRequestDto);
}
