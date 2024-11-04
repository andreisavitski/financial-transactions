package eu.senla.financialtransactions.service.rabbitmq;

import eu.senla.financialtransactions.dto.OperatorRequestDto;
import eu.senla.financialtransactions.dto.PaymentRequestDto;
import jakarta.validation.constraints.NotNull;
import org.springframework.amqp.core.Message;

public interface RabbitMqMessagePaymentSender {

    @NotNull
    Message sendMessageForPayment(@NotNull PaymentRequestDto paymentRequestDto);

    @NotNull
    Message sendMoneyToOperator(@NotNull OperatorRequestDto operatorRequestDto);
}
