package eu.senla.financialtransactions.service.rabbitmq.sender;

import eu.senla.financialtransactions.dto.DepositOpenerMessageDto;
import eu.senla.financialtransactions.dto.DepositUpdaterMessageDto;
import eu.senla.financialtransactions.service.rabbitmq.RabbitMqDepositSender;
import eu.senla.financialtransactions.service.rabbitmq.RabbitMqMessageSender;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static eu.senla.financialtransactions.constant.RabbitMqConstants.RABBITMQ_EXCHANGE_CARD;
import static eu.senla.financialtransactions.constant.RabbitMqConstants.RABBITMQ_ROUTING_KEY_FOR_REQUEST_OPEN_DEPOSIT;
import static eu.senla.financialtransactions.constant.RabbitMqConstants.RABBITMQ_ROUTING_KEY_FOR_REQUEST_UPDATE_DEPOSIT;

@Service
@RequiredArgsConstructor
public class RabbitMqDepositSenderImpl implements RabbitMqDepositSender {

    @Value(RABBITMQ_EXCHANGE_CARD)
    private String exchangeCard;

    @Value(RABBITMQ_ROUTING_KEY_FOR_REQUEST_OPEN_DEPOSIT)
    private String queueRequestForOpenDeposit;

    @Value(RABBITMQ_ROUTING_KEY_FOR_REQUEST_UPDATE_DEPOSIT)
    private String queueRequestForUpdateDeposit;

    private final RabbitMqMessageSender sender;

    @NotNull
    @Override
    public Message sendMessageForOpenDeposit(@NotNull DepositOpenerMessageDto depositDto) {
        return sender.convertAndSendMessage(
                depositDto, queueRequestForOpenDeposit, exchangeCard);
    }

    @NotNull
    @Override
    public Message sendMessageForUpdateDeposit(@NotNull DepositUpdaterMessageDto depositDto) {
        return sender.convertAndSendMessage(
                depositDto, queueRequestForUpdateDeposit, exchangeCard);
    }
}
