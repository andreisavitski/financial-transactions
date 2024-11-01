package eu.senla.financialtransactions.service.rabbitmq;

import org.springframework.amqp.core.Message;

public interface RabbitMqMessageSender {

    Message convertAndSendMessage(Object message, String routingKey, String exchange);
}
