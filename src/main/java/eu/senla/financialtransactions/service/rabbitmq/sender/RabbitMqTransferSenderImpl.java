package eu.senla.financialtransactions.service.rabbitmq.sender;

import eu.senla.financialtransactions.dto.TransferRequestDto;
import eu.senla.financialtransactions.service.rabbitmq.RabbitMqMessageSender;
import eu.senla.financialtransactions.service.rabbitmq.RabbitMqTransferSender;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static eu.senla.financialtransactions.constant.RabbitMqConstants.RABBITMQ_EXCHANGE_CARD;
import static eu.senla.financialtransactions.constant.RabbitMqConstants.RABBITMQ_ROUTING_KEY_FOR_REQUEST_OPEN_DEPOSIT;

@Service
@RequiredArgsConstructor
public class RabbitMqTransferSenderImpl implements RabbitMqTransferSender {

    @Value(RABBITMQ_EXCHANGE_CARD)
    private String exchangeCard;

    @Value(RABBITMQ_ROUTING_KEY_FOR_REQUEST_OPEN_DEPOSIT)
    private String queueRequestForTransfer;

    private final RabbitMqMessageSender sender;

    @NotNull
    @Override
    public Message sendMessageForTransfer(@NotNull TransferRequestDto transferRequestDto) {
        return sender.convertAndSendMessage(
                transferRequestDto, queueRequestForTransfer, exchangeCard);
    }
}
