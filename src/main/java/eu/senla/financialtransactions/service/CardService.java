package eu.senla.financialtransactions.service;

import eu.senla.financialtransactions.dto.Card;
import eu.senla.financialtransactions.dto.TransferRequestMessage;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

public interface CardService {

    List<Card> getClientCard(Long id)
            throws InterruptedException, IOException, TimeoutException;

    Boolean sendMessageToTransfer(TransferRequestMessage transferRequestMessage)
            throws InterruptedException, TimeoutException, IOException;
}
