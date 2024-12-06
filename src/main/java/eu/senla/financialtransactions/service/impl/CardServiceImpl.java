package eu.senla.financialtransactions.service.impl;

import eu.senla.financialtransactions.dto.CardDto;
import eu.senla.financialtransactions.dto.ClientCardRequestDto;
import eu.senla.financialtransactions.dto.MessageResponseDto;
import eu.senla.financialtransactions.dto.PaymentRequestMessageDto;
import eu.senla.financialtransactions.dto.TransferRequestDto;
import eu.senla.financialtransactions.service.CardService;
import eu.senla.financialtransactions.service.rabbitmq.RabbitMqMessageCardSender;
import eu.senla.financialtransactions.service.rabbitmq.RabbitMqMessagePaymentSender;
import eu.senla.financialtransactions.service.rabbitmq.RabbitMqMessageTransferSender;
import eu.senla.financialtransactions.util.MessageConverter;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    private final RabbitMqMessageCardSender cardSender;

    private final RabbitMqMessageTransferSender transferSender;

    private final RabbitMqMessagePaymentSender paymentSender;

    @Override
    public MessageResponseDto getClientCard(Long id) {
        final ClientCardRequestDto clientCardRequestDto = ClientCardRequestDto.builder()
                .id(id)
                .build();
        final Message message = cardSender.sendRequestForCard(clientCardRequestDto);
        final MessageResponseDto messageResponseDto =
                MessageConverter.convertToObj(message.getBody(), MessageResponseDto.class);
        final List<CardDto> cards =
                MessageConverter.convertToListObjects(messageResponseDto.getData(), CardDto.class);
        messageResponseDto.setData(cards);
        return messageResponseDto;

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
            @NotNull PaymentRequestMessageDto paymentRequestMessageDto) {
        final Message message = paymentSender.sendMessageForPayment(paymentRequestMessageDto);
        return MessageConverter.convertToObj(message.getBody(), MessageResponseDto.class);
    }
}
