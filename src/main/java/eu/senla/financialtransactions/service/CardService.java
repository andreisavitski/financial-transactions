package eu.senla.financialtransactions.service;

import eu.senla.financialtransactions.dto.MessageResponseDto;
import eu.senla.financialtransactions.dto.MessageResponseDtoTest;
import eu.senla.financialtransactions.dto.PaymentRequestDto;
import eu.senla.financialtransactions.dto.TransferRequestDto;
import jakarta.validation.constraints.NotNull;

public interface CardService {

    @NotNull
    MessageResponseDto getClientCard(@NotNull Long id);

    MessageResponseDtoTest getClientCardTest(@NotNull Long id);

    @NotNull
    MessageResponseDto executeTransferMoney(
            @NotNull TransferRequestDto transferRequestDto);

    @NotNull
    MessageResponseDto executeWithdrawalOfMoney(
            @NotNull PaymentRequestDto paymentRequestDto);
}
