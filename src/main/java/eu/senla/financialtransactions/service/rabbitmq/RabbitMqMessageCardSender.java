package eu.senla.financialtransactions.service.rabbitmq;

import eu.senla.financialtransactions.dto.ClientCardRequestDto;
import jakarta.validation.constraints.NotNull;
import org.springframework.amqp.core.Message;

public interface RabbitMqMessageCardSender {

    @NotNull
    Message sendRequestForCard(@NotNull ClientCardRequestDto clientCardRequestDto);
}
