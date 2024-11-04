package eu.senla.financialtransactions.service.rabbitmq.sender;

import eu.senla.financialtransactions.dto.MessageResponseDto;
import eu.senla.financialtransactions.dto.OperatorRequestDto;
import eu.senla.financialtransactions.dto.PaymentRequestDto;
import eu.senla.financialtransactions.service.rabbitmq.RabbitMqMessagePaymentSender;
import eu.senla.financialtransactions.service.rabbitmq.RabbitMqMessageSender;
import eu.senla.financialtransactions.util.MessageConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import static eu.senla.financialtransactions.constant.AppConstants.RABBITMQ_EXCHANGE_CARD;
import static eu.senla.financialtransactions.constant.AppConstants.RABBITMQ_ROUTING_KEY_FOR_REQUEST_PAYMENT;
import static eu.senla.financialtransactions.constant.AppStatusConstant.OK;

@Service
@RequiredArgsConstructor
public class RabbitMqMessagePaymentSenderImpl implements RabbitMqMessagePaymentSender {

    @Value(RABBITMQ_EXCHANGE_CARD)
    private String exchangeCardPayment;

    @Value(RABBITMQ_ROUTING_KEY_FOR_REQUEST_PAYMENT)
    private String queueRequestForPayment;

    private final RabbitMqMessageSender sender;

    @Override
    public Message sendMessageForPayment(PaymentRequestDto paymentRequestDto) {
        return sender.convertAndSendMessage(paymentRequestDto, exchangeCardPayment, queueRequestForPayment);
    }

    @Override
    public Message sendMoneyToOperator(OperatorRequestDto operatorRequestDto) {
        MessageResponseDto messageResponseDto = MessageResponseDto.builder()
                .status(OK)
                .data(new ArrayList<>())
                .exceptions(new ArrayList<>())
                .build();
        return new Message(MessageConverter.convertToBytes(messageResponseDto));
    }
}
