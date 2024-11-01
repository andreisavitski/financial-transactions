package eu.senla.financialtransactions.service.rabbitmq;

import eu.senla.financialtransactions.dto.ClientCardRequest;
import org.springframework.amqp.core.Message;

public interface RabbitMqMessageCardSender {

    Message sendRequestForCard(ClientCardRequest clientCardRequest);
}
