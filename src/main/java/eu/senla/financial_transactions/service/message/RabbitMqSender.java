package eu.senla.financial_transactions.service.message;

import eu.senla.financial_transactions.dto.ClientCardRequest;
import eu.senla.financial_transactions.manager.ExchangerManager;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeoutException;

import static eu.senla.financial_transactions.constant.AppConstants.RABBITMQ_EXCHANGE;
import static eu.senla.financial_transactions.constant.AppConstants.RABBITMQ_ROUTING1_KEY;
import static java.util.concurrent.TimeUnit.SECONDS;

@Service
@RequiredArgsConstructor
public class RabbitMqSender {

    @Value(RABBITMQ_EXCHANGE)
    private String exchange;

    @Value(RABBITMQ_ROUTING1_KEY)
    private String routingJsonKey;

    private final RabbitTemplate rabbitTemplate;

    private final ExchangerManager exchangerManager;

    public String sendClientId(ClientCardRequest message) throws InterruptedException, TimeoutException {
        String correlationId = exchangerManager.generateCorrelationId();
        Exchanger<String> exchanger = exchangerManager.createExchanger(correlationId);
        rabbitTemplate.convertAndSend(
                exchange,
                routingJsonKey,
                message,
                messagePostProcessor -> {
                    messagePostProcessor.getMessageProperties().setHeader("correlationId", correlationId);
                    return messagePostProcessor;
                });
        return exchanger.exchange(null, 10, SECONDS);
    }
}
