package eu.senla.financialtransactions.service.rabbitmq;

import jakarta.validation.constraints.NotNull;
import org.springframework.amqp.core.Message;

public interface RabbitMqMessageSender {

    @NotNull
    Message convertAndSendMessage(@NotNull Object message,
                                  @NotNull String routingKey,
                                  @NotNull String exchange);
}
