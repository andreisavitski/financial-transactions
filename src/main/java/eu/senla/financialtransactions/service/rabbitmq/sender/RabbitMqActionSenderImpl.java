package eu.senla.financialtransactions.service.rabbitmq.sender;

import eu.senla.financialtransactions.dto.ActionDto;
import eu.senla.financialtransactions.entity.Action;
import eu.senla.financialtransactions.service.rabbitmq.RabbitMqActionSender;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static eu.senla.financialtransactions.constant.RabbitMqConstants.RABBITMQ_EXCHANGE_CARD;
import static eu.senla.financialtransactions.constant.RabbitMqConstants.RABBITMQ_ROUTING_KEY_FOR_REQUEST_SAVE_ACTION;

@Service
@RequiredArgsConstructor
public class RabbitMqActionSenderImpl implements RabbitMqActionSender {

    private final RabbitTemplate rabbitTemplate;

    @Value(RABBITMQ_EXCHANGE_CARD)
    private String exchange;

    @Value(RABBITMQ_ROUTING_KEY_FOR_REQUEST_SAVE_ACTION)
    private String routingKeyForSaveAction;

    @SneakyThrows
    @Override
    public void saveAction(@NotNull Action message) {
        final ActionDto actionDto = ActionDto.builder()
                .data(message.getOperation())
                .build();
        rabbitTemplate.convertAndSend(
                exchange,
                routingKeyForSaveAction,
                actionDto);
    }
}
