package eu.senla.financialtransactions.service.rabbitmq;

import eu.senla.financialtransactions.dto.TransferRequestMessage;
import org.springframework.amqp.core.Message;

public interface RabbitMqMessageTransferSender {

    Message sendMessageForTransfer(TransferRequestMessage transferRequestMessage);
}
