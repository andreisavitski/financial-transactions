package eu.senla.financialtransactions.service.rabbitmq.sender;

import eu.senla.financialtransactions.dto.ClientCardRequestDto;
import eu.senla.financialtransactions.service.rabbitmq.RabbitMqCardSender;
import eu.senla.financialtransactions.service.rabbitmq.RabbitMqMessageSender;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static eu.senla.financialtransactions.constant.RabbitMqConstants.RABBITMQ_EXCHANGE_CARD;
import static eu.senla.financialtransactions.constant.RabbitMqConstants.RABBITMQ_ROUTING_KEY_FOR_REQUEST_ADD_CARD;
import static eu.senla.financialtransactions.constant.RabbitMqConstants.RABBITMQ_ROUTING_KEY_FOR_REQUEST_GET_CARD;

@Service
@RequiredArgsConstructor
public class RabbitMqCardSenderImpl implements RabbitMqCardSender {

    @Value(RABBITMQ_EXCHANGE_CARD)
    private String exchangeCard;

    @Value(RABBITMQ_ROUTING_KEY_FOR_REQUEST_GET_CARD)
    private String routingKeyForRequestGetCard;

    @Value(RABBITMQ_ROUTING_KEY_FOR_REQUEST_ADD_CARD)
    private String routingKeyForRequestAddCard;

    private final RabbitMqMessageSender sender;

    @NotNull
    @Override
    public Message sendRequestForGetCard(@NotNull ClientCardRequestDto clientCardRequestDto) {
        return sender.convertAndSendMessage(clientCardRequestDto,
                routingKeyForRequestGetCard, exchangeCard);
    }

    @NotNull
    @Override
    public Message sendRequestForAddCard(@NotNull ClientCardRequestDto clientCardRequestDto) {
        return sender.convertAndSendMessage(clientCardRequestDto,
                routingKeyForRequestAddCard, exchangeCard);
    }
}
