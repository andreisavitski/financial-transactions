package eu.senla.financialtransactions.service;

import eu.senla.financialtransactions.dto.TransferCheckRequestDto;
import eu.senla.financialtransactions.dto.TransferCheckResponseDto;
import eu.senla.financialtransactions.dto.TransferExecuteRequestDto;

public interface TransferService {

    TransferCheckResponseDto checkTransfer(TransferCheckRequestDto transferCheckRequest);

    void executeTransfer(TransferExecuteRequestDto transferExecuteRequestDto);
}
