package eu.senla.financial_transactions.service;

import eu.senla.financial_transactions.dto.ClientCardRequest;
import eu.senla.financial_transactions.model.Card;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

public interface CardService {
    List<Card> getClientCard(ClientCardRequest clientCardRequest)
            throws InterruptedException, IOException, TimeoutException;
}
