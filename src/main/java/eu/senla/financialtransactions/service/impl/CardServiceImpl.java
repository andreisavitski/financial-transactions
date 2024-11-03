package eu.senla.financialtransactions.service.impl;

import eu.senla.financialtransactions.dto.ClientCardRequestDto;
import eu.senla.financialtransactions.dto.TransferCheckRequestDto;
import eu.senla.financialtransactions.dto.TransferExecuteResponseDto;
import eu.senla.financialtransactions.service.CardService;
import eu.senla.financialtransactions.service.rabbitmq.RabbitMqMessageCardSender;
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

    @Override
    @NotNull
    public TransferExecuteResponseDto getClientCard(@NotNull Long id) {
        final ClientCardRequestDto clientCardRequestDto = ClientCardRequestDto.builder()
                .id(id)
                .build();
        final Message message = cardSender.sendRequestForCard(clientCardRequestDto);
        return MessageConverter.convertToObj(message.getBody(), TransferExecuteResponseDto.class);
    }

    @Override
    @NotNull
    public TransferExecuteResponseDto sendMessageForTransfer(@NotNull TransferCheckRequestDto transferCheckRequestDto) {
        final Message message = transferSender.sendMessageForTransfer(transferCheckRequestDto);
        return MessageConverter.convertToObj(message.getBody(), TransferExecuteResponseDto.class);
    }
}
