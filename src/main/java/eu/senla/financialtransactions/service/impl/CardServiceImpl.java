package eu.senla.financialtransactions.service.impl;

import eu.senla.financialtransactions.converter.MessageUtil;
import eu.senla.financialtransactions.dto.Card;
import eu.senla.financialtransactions.dto.ClientCardRequest;
import eu.senla.financialtransactions.dto.TransferRequestMessage;
import eu.senla.financialtransactions.service.CardService;
import eu.senla.financialtransactions.service.message.RabbitMqSender;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    private final RabbitMqSender rabbitMqSender;

    private final MessageUtil messageUtil;

    @Override
    public List<Card> getClientCard(Long id) {
        ClientCardRequest clientCardRequest = ClientCardRequest.builder()
                .id(id)
                .build();
        return messageUtil.convertToList(rabbitMqSender
                .sendRequestForCard(clientCardRequest).getBody(), Card.class);
    }

    @Override
    public Boolean sendMessageToTransfer(TransferRequestMessage transferRequestMessage) {
        Message message = rabbitMqSender.sendMessageForTransfer(transferRequestMessage);
        return messageUtil.convertToObj(message.getBody(), Boolean.class);
    }
}
