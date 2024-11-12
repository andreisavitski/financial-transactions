package eu.senla.financialtransactions.service.rabbitmq;

import eu.senla.financialtransactions.dto.TransferRequestDto;
import jakarta.validation.constraints.NotNull;
import org.springframework.amqp.core.Message;

public interface RabbitMqMessageTransferSender {

    @NotNull
    Message sendMessageForTransfer(@NotNull TransferRequestDto transferRequestDto);
}
