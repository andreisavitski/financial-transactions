package eu.senla.financialtransactions.service;

import eu.senla.financialtransactions.dto.TransferCheckRequestDto;
import eu.senla.financialtransactions.dto.TransferExecuteResponseDto;

public interface CardService {

    TransferExecuteResponseDto getClientCard(Long id);

    TransferExecuteResponseDto sendMessageForTransfer(TransferCheckRequestDto transferCheckRequestDto);
}
