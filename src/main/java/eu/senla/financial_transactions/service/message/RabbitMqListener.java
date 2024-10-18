package eu.senla.financial_transactions.service.message;

import eu.senla.financial_transactions.manager.ExchangerManager;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.util.concurrent.Exchanger;

import static eu.senla.financial_transactions.constant.AppConstants.RABBITMQ_QUEUE_2;

@Service
@RequiredArgsConstructor
public class RabbitMqListener {

    private final ExchangerManager exchangerManager;

    @RabbitListener(queues = {RABBITMQ_QUEUE_2})
    public void acceptRequestToReceiveAllCards(String response, @Header("correlationId") String correlationId)
            throws InterruptedException, Exception {
        Exchanger<String> exchanger = exchangerManager.getExchanger(correlationId);
        if (exchanger != null) {
            exchanger.exchange(response);
            exchangerManager.removeExchanger(correlationId);
        }
    }

//    @RabbitListener(queues = {RABBITMQ_QUEUE_2})
//    public String acceptMoneyTransferRequest(String message) {
//        return message;
//    }

}
