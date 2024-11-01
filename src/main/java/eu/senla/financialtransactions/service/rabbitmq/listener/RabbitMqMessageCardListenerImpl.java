package eu.senla.financialtransactions.service.rabbitmq.listener;

import eu.senla.financialtransactions.manager.ExchangerManager;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.Exchanger;

import static eu.senla.financialtransactions.constant.AppConstants.RABBITMQ_QUEUE_RESPONSE_FOR_GET_CARD;
import static eu.senla.financialtransactions.constant.AppConstants.RABBITMQ_QUEUE_RESPONSE_FOR_TRANSFER;

@Service
@RequiredArgsConstructor
public class RabbitMqMessageCardListenerImpl {

    private final ExchangerManager exchangerManager;

    @RabbitListener(queues = {RABBITMQ_QUEUE_RESPONSE_FOR_GET_CARD})
    public void acceptRequestToReceiveAllCards(Message message) {
        processMessage(message);
    }

    @RabbitListener(queues = {RABBITMQ_QUEUE_RESPONSE_FOR_TRANSFER})
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
