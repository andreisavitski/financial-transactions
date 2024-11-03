package eu.senla.financialtransactions.service.rabbitmq;

import eu.senla.financialtransactions.dto.ClientCardRequestDto;
import org.springframework.amqp.core.Message;

public interface RabbitMqMessageCardSender {

    Message sendRequestForCard(ClientCardRequestDto clientCardRequestDto);
}
