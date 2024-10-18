package eu.senla.financial_transactions.service.impl;

import eu.senla.financial_transactions.converter.JsonUtil;
import eu.senla.financial_transactions.dto.ClientCardRequest;
import eu.senla.financial_transactions.model.Card;
import eu.senla.financial_transactions.service.CardService;
import eu.senla.financial_transactions.service.message.RabbitMqSender;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    private final RabbitMqSender rabbitMqSender;

    private final JsonUtil jsonUtil;

    @Override
    public List<Card> getClientCard(ClientCardRequest clientCardRequest)
            throws InterruptedException, IOException, TimeoutException {
        return jsonUtil.fromJson(rabbitMqSender.sendClientId(clientCardRequest), Card.class);
    }
}
