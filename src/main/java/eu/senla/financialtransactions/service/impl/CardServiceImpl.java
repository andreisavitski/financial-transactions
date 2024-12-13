package eu.senla.financialtransactions.service.impl;

import eu.senla.financialtransactions.dto.CardDto;
import eu.senla.financialtransactions.dto.ClientCardRequestDto;
import eu.senla.financialtransactions.dto.MessageResponseDto;
import eu.senla.financialtransactions.dto.PaymentRequestMessageDto;
import eu.senla.financialtransactions.dto.TransferRequestDto;
import eu.senla.financialtransactions.exception.ApplicationException;
import eu.senla.financialtransactions.repository.ClientRepository;
import eu.senla.financialtransactions.service.CardService;
import eu.senla.financialtransactions.service.rabbitmq.RabbitMqCardSender;
import eu.senla.financialtransactions.service.rabbitmq.RabbitMqPaymentSender;
import eu.senla.financialtransactions.service.rabbitmq.RabbitMqTransferSender;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Service;

import java.util.List;

import static eu.senla.financialtransactions.exception.ApplicationError.CLIENT_NOT_FOUND;
import static eu.senla.financialtransactions.util.MessageConverter.convertToListObjects;
import static eu.senla.financialtransactions.util.MessageConverter.convertToObj;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    private final RabbitMqCardSender cardSender;

    private final RabbitMqTransferSender transferSender;

    private final RabbitMqPaymentSender paymentSender;

    private final ClientRepository clientRepository;

    @NotNull
    @Override
    public MessageResponseDto getClientCard(@NotNull Long id) {
        final ClientCardRequestDto clientCardRequestDto = ClientCardRequestDto.builder()
                .id(id)
                .build();
        final Message message = cardSender.sendRequestForGetCard(clientCardRequestDto);
        final MessageResponseDto messageResponseDto =
                convertToObj(message.getBody(), MessageResponseDto.class);
        final List<CardDto> cards = convertToListObjects(messageResponseDto.getData(), CardDto.class);
        messageResponseDto.setData(cards);
        return messageResponseDto;
    }

    @NotNull
    @Override
    public MessageResponseDto executeTransferMoney(
            @NotNull TransferRequestDto transferRequestDto) {
        final Message message = transferSender.sendMessageForTransfer(transferRequestDto);
        return convertToObj(message.getBody(), MessageResponseDto.class);
    }

    @NotNull
    @Override
    public MessageResponseDto executeWithdrawalOfMoney(
            @NotNull PaymentRequestMessageDto paymentRequestMessageDto) {
        final Message message = paymentSender.sendMessageForPayment(paymentRequestMessageDto);
        return convertToObj(message.getBody(), MessageResponseDto.class);
    }

    @NotNull
    @Override
    public MessageResponseDto addCard(@NotNull ClientCardRequestDto clientCardRequestDto) {
        if (clientRepository.findById(clientCardRequestDto.getId()).isPresent()) {
            final Message message = cardSender.sendRequestForAddCard(clientCardRequestDto);
            return convertToObj(message.getBody(), MessageResponseDto.class);
        }
        throw new ApplicationException(CLIENT_NOT_FOUND);
    }
}
