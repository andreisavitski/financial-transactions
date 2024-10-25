package eu.senla.financialtransactions.service.message;

import eu.senla.financialtransactions.manager.ExchangerManager;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.Exchanger;

import static eu.senla.financialtransactions.constant.AppConstants.RABBITMQ_QUEUE_2;
import static eu.senla.financialtransactions.constant.AppConstants.RABBITMQ_QUEUE_4;

@Service
@RequiredArgsConstructor
public class RabbitMqListener {

    private final ExchangerManager exchangerManager;

    @RabbitListener(queues = {RABBITMQ_QUEUE_2})
    public void acceptRequestToReceiveAllCards(Message message) {
        processMessage(message);
    }

    @RabbitListener(queues = {RABBITMQ_QUEUE_4})
    public void acceptMoneyTransferRequest(Message message) {
        processMessage(message);
    }

    @SneakyThrows
    private void processMessage(Message message) {
        String correlationId = message.getMessageProperties().getCorrelationId();
        Exchanger<Message> exchanger = exchangerManager.getExchanger(correlationId);
        if (exchanger != null) {
            exchanger.exchange(message);
            exchangerManager.removeExchanger(correlationId);
        }
    }
}
