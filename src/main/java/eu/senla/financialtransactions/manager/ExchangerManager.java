package eu.senla.financialtransactions.manager;

import jakarta.validation.constraints.NotNull;
import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Exchanger;

@Component
public class ExchangerManager {

    private final Map<String, Exchanger<Message>> exchangerMap = new ConcurrentHashMap<>();

    @NotNull
    public String generateCorrelationId() {
        return UUID.randomUUID().toString();
    }

    @NotNull
    public Exchanger<Message> createExchanger(@NotNull String correlationId) {
        final Exchanger<Message> exchanger = new Exchanger<>();
        exchangerMap.put(correlationId, exchanger);
        return exchanger;
    }

    @NotNull
    public Exchanger<Message> getExchanger(@NotNull String correlationId) {
        return exchangerMap.get(correlationId);
    }

    public void removeExchanger(@NotNull String correlationId) {
        exchangerMap.remove(correlationId);
    }
}
