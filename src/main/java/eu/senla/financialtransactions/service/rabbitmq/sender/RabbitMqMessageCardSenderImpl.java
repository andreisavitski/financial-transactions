package eu.senla.financialtransactions.service.rabbitmq.sender;

import eu.senla.financialtransactions.dto.ClientCardRequestDto;
import eu.senla.financialtransactions.service.rabbitmq.RabbitMqMessageCardSender;
import eu.senla.financialtransactions.service.rabbitmq.RabbitMqMessageSender;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static eu.senla.financialtransactions.constant.AppConstants.RABBITMQ_EXCHANGE_CARD_TRANSFER;
import static eu.senla.financialtransactions.constant.AppConstants.RABBITMQ_ROUTING_KEY_FOR_REQUEST_GET_CARD;

@Service
@RequiredArgsConstructor
public class RabbitMqMessageCardSenderImpl implements RabbitMqMessageCardSender {

    @Value(RABBITMQ_EXCHANGE_CARD_TRANSFER)
    private String exchangeCardTransfer;

    @Value(RABBITMQ_ROUTING_KEY_FOR_REQUEST_GET_CARD)
    private String routingKeyForRequestGetCard;

    private final RabbitMqMessageSender sender;

    @Override
    public Message sendRequestForCard(ClientCardRequestDto clientCardRequestDto) {
        return sender.convertAndSendMessage(clientCardRequestDto,
                routingKeyForRequestGetCard, exchangeCardTransfer);
    }
}
