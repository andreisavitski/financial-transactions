package eu.senla.financialtransactions.service.rabbitmq;

import eu.senla.financialtransactions.entity.Action;
import jakarta.validation.constraints.NotNull;

public interface RabbitMqActionSender {

    void saveAction(@NotNull Action action);
}
