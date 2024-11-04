package eu.senla.financialtransactions.service.impl;

import eu.senla.financialtransactions.dto.*;
import eu.senla.financialtransactions.service.CardService;
import eu.senla.financialtransactions.service.rabbitmq.RabbitMqMessageCardSender;
import eu.senla.financialtransactions.service.rabbitmq.RabbitMqMessagePaymentSender;
import eu.senla.financialtransactions.service.rabbitmq.RabbitMqMessageTransferSender;
import eu.senla.financialtransactions.util.MessageConverter;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    private final RabbitMqMessageCardSender cardSender;

    private final RabbitMqMessageTransferSender transferSender;

    private final RabbitMqMessagePaymentSender paymentSender;

    @NotNull
    @Override
    public MessageResponseDto getClientCard(@NotNull Long id) {
        final ClientCardRequestDto clientCardRequestDto = ClientCardRequestDto.builder()
                .id(id)
                .build();
        final Message message = cardSender.sendRequestForCard(clientCardRequestDto);
        return MessageConverter.convertToObj(message.getBody(), MessageResponseDto.class);
    }

    @Override
    public MessageResponseDtoTest getClientCardTest(Long id) {
        final ClientCardRequestDto clientCardRequestDto = ClientCardRequestDto.builder()
                .id(id)
                .build();
        final Message message = cardSender.sendRequestForCard(clientCardRequestDto);
        return MessageConverter.convertToObj(message.getBody(), MessageResponseDtoTest.class);
    }

    @NotNull
    @Override
    public MessageResponseDto executeTransferMoney(
            @NotNull TransferRequestDto transferRequestDto) {
        final Message message = transferSender.sendMessageForTransfer(transferRequestDto);
        return MessageConverter.convertToObj(message.getBody(), MessageResponseDto.class);
    }

    @NotNull
    @Override
    public MessageResponseDto executeWithdrawalOfMoney(
            @NotNull PaymentRequestDto paymentRequestDto) {
        final Message message = paymentSender.sendMessageForPayment(paymentRequestDto);
        return MessageConverter.convertToObj(message.getBody(), MessageResponseDto.class);
    }
}
