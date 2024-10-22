package eu.senla.financialtransactions.service.message;

import eu.senla.financialtransactions.dto.ClientCardRequest;
import eu.senla.financialtransactions.dto.TransferRequestMessage;
import eu.senla.financialtransactions.manager.ExchangerManager;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeoutException;

import static eu.senla.financialtransactions.constant.AppConstants.*;
import static java.util.concurrent.TimeUnit.SECONDS;

@Service
@RequiredArgsConstructor
public class RabbitMqSender {

    @Value(RABBITMQ_EXCHANGE)
    private String exchange;

    @Value(RABBITMQ_ROUTING1_KEY)
    private String routing1JsonKey;

    @Value(RABBITMQ_ROUTING3_KEY)
    private String routing3JsonKey;

    private final RabbitTemplate rabbitTemplate;

    private final ExchangerManager exchangerManager;

    public String sendClientId(ClientCardRequest message)
            throws InterruptedException, TimeoutException {
        String correlationId = exchangerManager.generateCorrelationId();
        Exchanger<String> exchanger = exchangerManager.createExchanger(correlationId);
        rabbitTemplate.convertAndSend(
                exchange,
                routing1JsonKey,
                message,
                messagePostProcessor -> {
                    messagePostProcessor.getMessageProperties().setHeader("correlationId1", correlationId);
                    return messagePostProcessor;
                });
        return exchanger.exchange(null, 5, SECONDS);
    }

    public String sendMessageToTransfer(TransferRequestMessage message)
            throws InterruptedException, TimeoutException {
        String correlationId = exchangerManager.generateCorrelationId();
        Exchanger<String> exchanger = exchangerManager.createExchanger(correlationId);
        rabbitTemplate.convertAndSend(
                exchange,
                routing3JsonKey,
                message,
                messagePostProcessor -> {
                    messagePostProcessor.getMessageProperties().setHeader("correlationId2", correlationId);
                    return messagePostProcessor;
                });
        return exchanger.exchange(null, 5, SECONDS);
    }
}
