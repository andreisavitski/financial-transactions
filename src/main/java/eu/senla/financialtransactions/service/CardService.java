package eu.senla.financialtransactions.service;

import eu.senla.financialtransactions.dto.Card;
import eu.senla.financialtransactions.dto.TransferRequestMessage;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface CardService {

    List<Card> getClientCard(Long id);

    HttpStatus sendMessageForTransfer(TransferRequestMessage transferRequestMessage);
}
