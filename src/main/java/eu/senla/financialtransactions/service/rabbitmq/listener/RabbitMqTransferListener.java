package eu.senla.financialtransactions.service.rabbitmq.listener;

import eu.senla.financialtransactions.util.MessageHandler;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import static eu.senla.financialtransactions.constant.RabbitMqConstants.RABBITMQ_QUEUE_RESPONSE_FOR_TRANSFER;

@Service
@RequiredArgsConstructor
public class RabbitMqTransferListener {

    private final MessageHandler messageHandler;

    @RabbitListener(queues = {RABBITMQ_QUEUE_RESPONSE_FOR_TRANSFER})
    public void acceptMoneyTransferRequest(@NotNull Message message) {
        messageHandler.processMessage(message);
    }
}
