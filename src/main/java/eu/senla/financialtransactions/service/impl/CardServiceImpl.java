package eu.senla.financialtransactions.service.impl;

import eu.senla.financialtransactions.converter.MessageUtil;
import eu.senla.financialtransactions.dto.Card;
import eu.senla.financialtransactions.dto.ClientCardRequest;
import eu.senla.financialtransactions.dto.ClientCardResponseMessage;
import eu.senla.financialtransactions.dto.TransferRequestMessage;
import eu.senla.financialtransactions.exception.ApplicationException;
import eu.senla.financialtransactions.service.CardService;
import eu.senla.financialtransactions.service.message.RabbitMqSender;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

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

//        byte[] body = rabbitMqSender.sendRequestForCard(clientCardRequest).getBody();
//        ClientCardResponseMessage clientCardResponseMessage = messageUtil.convertToObj(body, ClientCardResponseMessage.class);
//        ClientCardResponseMessage clientCardResponseMessage = messageUtil.convertToObj(body, ClientCardResponseMessage.class);
//
//        if (clientCardResponseMessage.getApplicationException().getStatus() != OK) {
//            throw new ApplicationException(
//                    clientCardResponseMessage.getApplicationException().getStatus(),
//                    clientCardResponseMessage.getApplicationException().getMessage(),
//                    clientCardResponseMessage.getApplicationException().getCode());
//        }
//        return clientCardResponseMessage.getCards();

        return messageUtil.convertToList(rabbitMqSender
                .sendRequestForCard(clientCardRequest).getBody(), Card.class);
    }

    @Override
    public Boolean sendMessageToTransfer(TransferRequestMessage transferRequestMessage) {
        Message message = rabbitMqSender.sendMessageForTransfer(transferRequestMessage);
        return messageUtil.convertToObj(message.getBody(), Boolean.class);
    }
}
