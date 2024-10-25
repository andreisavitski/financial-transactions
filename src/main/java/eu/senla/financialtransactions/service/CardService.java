package eu.senla.financialtransactions.service;

import eu.senla.financialtransactions.dto.Card;
import eu.senla.financialtransactions.dto.TransferRequestMessage;

import java.util.List;

public interface CardService {

    List<Card> getClientCard(Long id);

    Boolean sendMessageToTransfer(TransferRequestMessage transferRequestMessage);
}
