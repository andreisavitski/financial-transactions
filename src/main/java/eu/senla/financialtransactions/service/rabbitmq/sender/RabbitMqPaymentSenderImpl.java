package eu.senla.financialtransactions.service.rabbitmq.sender;

import eu.senla.financialtransactions.dto.PaymentRequestMessageDto;
import eu.senla.financialtransactions.service.rabbitmq.RabbitMqMessageSender;
import eu.senla.financialtransactions.service.rabbitmq.RabbitMqPaymentSender;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static eu.senla.financialtransactions.constant.RabbitMqConstants.RABBITMQ_EXCHANGE_CARD;
import static eu.senla.financialtransactions.constant.RabbitMqConstants.RABBITMQ_ROUTING_KEY_FOR_REQUEST_PAYMENT;

@Service
@RequiredArgsConstructor
public class RabbitMqPaymentSenderImpl implements RabbitMqPaymentSender {

    @Value(RABBITMQ_EXCHANGE_CARD)
    private String exchangeCard;

    @Value(RABBITMQ_ROUTING_KEY_FOR_REQUEST_PAYMENT)
    private String queueRequestForPayment;

    private final RabbitMqMessageSender sender;

    @NotNull
    @Override
    public Message sendMessageForPayment(
            @NotNull PaymentRequestMessageDto paymentRequestMessageDto) {
        return sender.convertAndSendMessage(
                paymentRequestMessageDto, queueRequestForPayment, exchangeCard);
    }
}
