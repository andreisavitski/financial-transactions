package eu.senla.financialtransactions.service.rabbitmq;

import eu.senla.financialtransactions.dto.PaymentRequestMessageDto;
import jakarta.validation.constraints.NotNull;
import org.springframework.amqp.core.Message;

public interface RabbitMqMessagePaymentSender {

    @NotNull
    Message sendMessageForPayment(@NotNull PaymentRequestMessageDto paymentRequestMessageDto);
}
