package eu.senla.financialtransactions.service;

import eu.senla.financialtransactions.dto.MessageResponseDto;
import eu.senla.financialtransactions.dto.TransferRequestDto;
import eu.senla.financialtransactions.dto.UuidDto;
import jakarta.validation.constraints.NotNull;

public interface TransferService {

    @NotNull
    UuidDto checkTransfer(
            @NotNull TransferRequestDto transferCheckRequest);

    @NotNull
    MessageResponseDto executeTransfer(
            @NotNull UuidDto uuidDto);
}
