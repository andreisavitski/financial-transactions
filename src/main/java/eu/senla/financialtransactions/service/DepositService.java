package eu.senla.financialtransactions.service;

import eu.senla.financialtransactions.dto.DepositOpenerMessageDto;
import eu.senla.financialtransactions.dto.DepositUpdaterMessageDto;
import eu.senla.financialtransactions.dto.MessageResponseDto;
import jakarta.validation.constraints.NotNull;

public interface DepositService {

    @NotNull
    MessageResponseDto openDeposit(@NotNull DepositOpenerMessageDto depositDto);

    @NotNull
    MessageResponseDto updateDeposit(@NotNull DepositUpdaterMessageDto depositDto);
}
