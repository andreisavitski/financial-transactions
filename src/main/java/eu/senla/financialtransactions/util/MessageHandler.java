package eu.senla.financialtransactions.util;

import eu.senla.financialtransactions.manager.ExchangerManager;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Component;

import java.util.concurrent.Exchanger;

@Component
@RequiredArgsConstructor
public class MessageHandler {

    private final ExchangerManager exchangerManager;

    @SneakyThrows
    public void processMessage(@NotNull Message message) {
        final String correlationId = message.getMessageProperties().getCorrelationId();
        final Exchanger<Message> exchanger = exchangerManager.getExchanger(correlationId);
        exchanger.exchange(message);
        exchangerManager.removeExchanger(correlationId);
    }
}
