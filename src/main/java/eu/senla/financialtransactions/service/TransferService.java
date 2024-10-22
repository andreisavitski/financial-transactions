package eu.senla.financialtransactions.service;

import eu.senla.financialtransactions.dto.TransferCheckResponseDto;
import eu.senla.financialtransactions.dto.TransferCheckerRequestDto;
import eu.senla.financialtransactions.dto.TransferExecuteRequestDto;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public interface TransferService {

    TransferCheckResponseDto checkTransfer(TransferCheckerRequestDto transferCheckRequest)
            throws InterruptedException, TimeoutException, IOException;

    void executeTransfer(TransferExecuteRequestDto transferExecuteRequestDto)
            throws IOException, InterruptedException, TimeoutException;
}
