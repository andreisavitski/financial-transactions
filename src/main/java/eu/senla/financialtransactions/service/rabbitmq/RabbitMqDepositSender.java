package eu.senla.financialtransactions.service.rabbitmq;

import eu.senla.financialtransactions.dto.DepositOpenerMessageDto;
import eu.senla.financialtransactions.dto.DepositUpdaterMessageDto;
import jakarta.validation.constraints.NotNull;
import org.springframework.amqp.core.Message;

public interface RabbitMqDepositSender {

    @NotNull
    Message sendMessageForOpenDeposit(@NotNull DepositOpenerMessageDto depositDto);

    @NotNull
    Message sendMessageForUpdateDeposit(@NotNull DepositUpdaterMessageDto depositDto);
}
