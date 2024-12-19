package eu.senla.financialtransactions.service.rabbitmq;

import eu.senla.financialtransactions.dto.ClientCardRequestDto;
import jakarta.validation.constraints.NotNull;
import org.springframework.amqp.core.Message;

public interface RabbitMqCardSender {

    @NotNull
    Message sendRequestForGetCard(@NotNull ClientCardRequestDto clientCardRequestDto);

    @NotNull
    Message sendRequestForAddCard(@NotNull ClientCardRequestDto clientCardRequestDto);
}
