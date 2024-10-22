package eu.senla.financialtransactions.service.message;

import eu.senla.financialtransactions.manager.ExchangerManager;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.util.concurrent.Exchanger;

import static eu.senla.financialtransactions.constant.AppConstants.RABBITMQ_QUEUE_2;
import static eu.senla.financialtransactions.constant.AppConstants.RABBITMQ_QUEUE_4;

@Service
@RequiredArgsConstructor
public class RabbitMqListener {

    private final ExchangerManager exchangerManager;

    @RabbitListener(queues = {RABBITMQ_QUEUE_2})
    public void acceptRequestToReceiveAllCards(String response,
                                               @Header("correlationId1") String correlationId)
            throws InterruptedException, Exception {
        Exchanger<String> exchanger = exchangerManager.getExchanger(correlationId);
        if (exchanger != null) {
            exchanger.exchange(response);
            exchangerManager.removeExchanger(correlationId);
        }
    }

    @RabbitListener(queues = {RABBITMQ_QUEUE_4})
    public void acceptMoneyTransferRequest(String response,
                                           @Header("correlationId2") String correlationId)
            throws InterruptedException {
        Exchanger<String> exchanger = exchangerManager.getExchanger(correlationId);
        if (exchanger != null) {
            exchanger.exchange(response);
            exchangerManager.removeExchanger(correlationId);
        }
    }

}
