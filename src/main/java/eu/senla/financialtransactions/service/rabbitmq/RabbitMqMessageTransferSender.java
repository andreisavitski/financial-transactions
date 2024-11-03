package eu.senla.financialtransactions.service.rabbitmq;

import eu.senla.financialtransactions.dto.TransferCheckRequestDto;
import org.springframework.amqp.core.Message;

public interface RabbitMqMessageTransferSender {

    Message sendMessageForTransfer(TransferCheckRequestDto transferCheckRequestDto);
}
