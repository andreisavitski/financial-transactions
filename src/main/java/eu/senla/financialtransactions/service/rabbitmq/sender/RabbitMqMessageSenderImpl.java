package eu.senla.financialtransactions.service.rabbitmq.sender;

import eu.senla.financialtransactions.manager.ExchangerManager;
import eu.senla.financialtransactions.service.rabbitmq.RabbitMqMessageSender;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.Exchanger;

import static eu.senla.financialtransactions.constant.AppConstants.TIMEOUT_EXCHANGER;
import static java.util.concurrent.TimeUnit.SECONDS;

@Service
@RequiredArgsConstructor
public class RabbitMqMessageSenderImpl implements RabbitMqMessageSender {

    private final RabbitTemplate rabbitTemplate;

    private final ExchangerManager exchangerManager;

    @NotNull
    @SneakyThrows
    @Override
    public Message convertAndSendMessage(@NotNull Object message,
                                         @NotNull String routingKey,
                                         @NotNull String exchange) {
        final String correlationId = exchangerManager.generateCorrelationId();
        final Exchanger<Message> exchanger = exchangerManager.createExchanger(correlationId);
        rabbitTemplate.convertAndSend(
                exchange,
                routingKey,
                message,
                messagePostProcessor -> {
                    messagePostProcessor.getMessageProperties().setCorrelationId(correlationId);
                    return messagePostProcessor;
                });
        return exchanger.exchange(null, TIMEOUT_EXCHANGER, SECONDS);
    }
}
