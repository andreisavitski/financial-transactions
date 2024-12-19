package eu.senla.financialtransactions.service.rabbitmq.listener;

import eu.senla.financialtransactions.util.MessageHandler;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import static eu.senla.financialtransactions.constant.RabbitMqConstants.RABBITMQ_QUEUE_RESPONSE_FOR_OPEN_DEPOSIT;
import static eu.senla.financialtransactions.constant.RabbitMqConstants.RABBITMQ_QUEUE_RESPONSE_FOR_UPDATE_DEPOSIT;

@Service
@RequiredArgsConstructor
public class RabbitMqDepositListener {

    private final MessageHandler messageHandler;

    @RabbitListener(queues = {RABBITMQ_QUEUE_RESPONSE_FOR_OPEN_DEPOSIT})
    public void acceptRequestToOpenDeposit(@NotNull Message message) {
        messageHandler.processMessage(message);
    }

    @RabbitListener(queues = {RABBITMQ_QUEUE_RESPONSE_FOR_UPDATE_DEPOSIT})
    public void acceptRequestToUpdateDeposit(@NotNull Message message) {
        messageHandler.processMessage(message);
    }
}
