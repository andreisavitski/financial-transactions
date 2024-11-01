package eu.senla.financialtransactions.service.rabbitmq.sender;

import eu.senla.financialtransactions.manager.ExchangerManager;
import eu.senla.financialtransactions.service.rabbitmq.RabbitMqMessageSender;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.Exchanger;

import static java.util.concurrent.TimeUnit.SECONDS;

@Service
@RequiredArgsConstructor
public class RabbitMqMessageSenderImpl implements RabbitMqMessageSender {

    private final RabbitTemplate rabbitTemplate;

    private final ExchangerManager exchangerManager;

    @SneakyThrows
    @Override
    public Message convertAndSendMessage(Object message, String routingKey, String exchange) {
        String correlationId = exchangerManager.generateCorrelationId();
        Exchanger<Message> exchanger = exchangerManager.createExchanger(correlationId);
        rabbitTemplate.convertAndSend(
                exchange,
                routingKey,
                message,
                messagePostProcessor -> {
                    messagePostProcessor.getMessageProperties().setCorrelationId(correlationId);
                    return messagePostProcessor;
                });
        return exchanger.exchange(null, 5, SECONDS);
    }
}
