package eu.senla.financialtransactions.service.impl;

import eu.senla.financialtransactions.converter.JsonUtil;
import eu.senla.financialtransactions.dto.Card;
import eu.senla.financialtransactions.dto.ClientCardRequest;
import eu.senla.financialtransactions.dto.TransferRequestMessage;
import eu.senla.financialtransactions.service.CardService;
import eu.senla.financialtransactions.service.message.RabbitMqSender;
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
    public List<Card> getClientCard(Long id)
            throws InterruptedException, IOException, TimeoutException {
        ClientCardRequest clientCardRequest = ClientCardRequest.builder()
                .id(id)
                .build();
        return jsonUtil.fromJsonToList(rabbitMqSender.sendClientId(clientCardRequest), Card.class);
    }

    @Override
    public Boolean sendMessageToTransfer(TransferRequestMessage transferRequestMessage)
            throws InterruptedException, TimeoutException, IOException {
        String responseMessage = rabbitMqSender.sendMessageToTransfer(transferRequestMessage);
        return jsonUtil.fromJsonToObj(responseMessage, Boolean.class);
    }
}
