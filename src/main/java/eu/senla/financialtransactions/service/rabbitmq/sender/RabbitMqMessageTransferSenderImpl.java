package eu.senla.financialtransactions.service.rabbitmq.sender;

import eu.senla.financialtransactions.dto.TransferRequestDto;
import eu.senla.financialtransactions.service.rabbitmq.RabbitMqMessageSender;
import eu.senla.financialtransactions.service.rabbitmq.RabbitMqMessageTransferSender;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static eu.senla.financialtransactions.constant.AppConstants.RABBITMQ_EXCHANGE_CARD;
import static eu.senla.financialtransactions.constant.AppConstants.RABBITMQ_ROUTING_KEY_FOR_REQUEST_TRANSFER;

@Service
@RequiredArgsConstructor
public class RabbitMqMessageTransferSenderImpl implements RabbitMqMessageTransferSender {

    @Value(RABBITMQ_EXCHANGE_CARD)
    private String exchangeCard;

    @Value(RABBITMQ_ROUTING_KEY_FOR_REQUEST_TRANSFER)
    private String queueRequestForTransfer;

    private final RabbitMqMessageSender sender;

    @NotNull
    @Override
    public Message sendMessageForTransfer(@NotNull TransferRequestDto transferRequestDto) {
        return sender.convertAndSendMessage(
                transferRequestDto, queueRequestForTransfer, exchangeCard);
    }
}
