package eu.senla.financialtransactions.service;

import eu.senla.financialtransactions.dto.TransferCheckResponseDto;
import eu.senla.financialtransactions.dto.TransferCheckerRequestDto;
import eu.senla.financialtransactions.dto.TransferExecuteRequestDto;

public interface TransferService {

    TransferCheckResponseDto checkTransfer(TransferCheckerRequestDto transferCheckRequest);

    void executeTransfer(TransferExecuteRequestDto transferExecuteRequestDto);
}
