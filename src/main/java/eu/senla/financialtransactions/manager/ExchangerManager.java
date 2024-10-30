package eu.senla.financialtransactions.manager;

import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Exchanger;

@Component
public class ExchangerManager {

    private final Map<String, Exchanger<Message>> exchangerMap = new ConcurrentHashMap<>();

    public String generateCorrelationId() {
        return UUID.randomUUID().toString();
    }

    public Exchanger<Message> createExchanger(String correlationId) {
        Exchanger<Message> exchanger = new Exchanger<>();
        exchangerMap.put(correlationId, exchanger);
        return exchanger;
    }

    public Exchanger<Message> getExchanger(String correlationId) {
        return exchangerMap.get(correlationId);
    }

    public void removeExchanger(String correlationId) {
        exchangerMap.remove(correlationId);
    }
}
