package eu.senla.financialtransactions.service;

import eu.senla.financialtransactions.dto.TransferExecuteResponseDto;
import eu.senla.financialtransactions.dto.TransferCheckRequestDto;
import eu.senla.financialtransactions.dto.TransferCheckResponseDto;
import eu.senla.financialtransactions.dto.TransferExecuteRequestDto;

public interface TransferService {

    TransferCheckResponseDto checkTransfer(TransferCheckRequestDto transferCheckRequest);

    TransferExecuteResponseDto executeTransfer(TransferExecuteRequestDto transferExecuteRequestDto);
}
