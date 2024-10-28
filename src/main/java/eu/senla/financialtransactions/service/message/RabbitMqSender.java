package eu.senla.financialtransactions.service.message;

import eu.senla.financialtransactions.dto.ClientCardRequest;
import eu.senla.financialtransactions.dto.TransferRequestMessage;
import eu.senla.financialtransactions.manager.ExchangerManager;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.Exchanger;

import static eu.senla.financialtransactions.constant.AppConstants.*;
import static java.util.concurrent.TimeUnit.SECONDS;

@Service
@RequiredArgsConstructor
public class RabbitMqSender {

    @Value(RABBITMQ_EXCHANGE)
    private String exchange;

    @Value(RABBITMQ_ROUTING_KEY_FOR_REQUEST_GET_CARD)
    private String routingKeyForRequestGetCard;

    @Value(RABBITMQ_ROUTING_KEY_FOR_REQUEST_TRANSFER)
    private String queueRequestForTransfer;

    private final RabbitTemplate rabbitTemplate;

    private final ExchangerManager exchangerManager;

    public Message sendRequestForCard(ClientCardRequest clientCardRequest) {
        return convertAndSendMessage(clientCardRequest, routingKeyForRequestGetCard);
    }

    public Message sendMessageForTransfer(TransferRequestMessage transferRequestMessage) {
        return convertAndSendMessage(transferRequestMessage, queueRequestForTransfer);
    }

    @SneakyThrows
    private Message convertAndSendMessage(Object message, String routingJsonKey) {
        String correlationId = exchangerManager.generateCorrelationId();
        Exchanger<Message> exchanger = exchangerManager.createExchanger(correlationId);
        rabbitTemplate.convertAndSend(
                exchange,
                routingJsonKey,
                message,
                messagePostProcessor -> {
                    messagePostProcessor.getMessageProperties().setCorrelationId(correlationId);
                    return messagePostProcessor;
                });
        return exchanger.exchange(null, 5, SECONDS);
    }
}
