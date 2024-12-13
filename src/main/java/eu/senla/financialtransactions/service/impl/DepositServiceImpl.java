package eu.senla.financialtransactions.service.impl;

import eu.senla.financialtransactions.dto.CardDto;
import eu.senla.financialtransactions.dto.DepositOpenerMessageDto;
import eu.senla.financialtransactions.dto.DepositUpdaterMessageDto;
import eu.senla.financialtransactions.dto.MessageResponseDto;
import eu.senla.financialtransactions.entity.Deposit;
import eu.senla.financialtransactions.entity.DepositType;
import eu.senla.financialtransactions.exception.ApplicationException;
import eu.senla.financialtransactions.repository.DepositRepository;
import eu.senla.financialtransactions.repository.DepositTypeRepository;
import eu.senla.financialtransactions.service.CardService;
import eu.senla.financialtransactions.service.DepositService;
import eu.senla.financialtransactions.service.rabbitmq.RabbitMqDepositSender;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static eu.senla.financialtransactions.exception.ApplicationError.DEPOSIT_NOT_FOUND;
import static eu.senla.financialtransactions.exception.ApplicationError.INCORRECT_DATA;
import static eu.senla.financialtransactions.util.MessageConverter.convertToListObjects;
import static eu.senla.financialtransactions.util.MessageConverter.convertToObj;

@Service
@RequiredArgsConstructor
public class DepositServiceImpl implements DepositService {

    private final CardService cardService;

    private final DepositTypeRepository depositTypeRepository;

    private final DepositRepository depositRepository;

    private final RabbitMqDepositSender sender;

    @NotNull
    @Override
    public MessageResponseDto openDeposit(@NotNull DepositOpenerMessageDto depositDto) {
        final Optional<DepositType> optionalDepositType =
                depositTypeRepository.findById(depositDto.getDepositTypeId());
        final Object objectCards = cardService.getClientCard(depositDto.getClientId()).getData();
        final List<CardDto> cards = convertToListObjects(objectCards, CardDto.class);
        boolean isThereCard = cards.stream()
                .noneMatch(card -> card.getId().equals(depositDto.getCardId()));
        if (!isThereCard && optionalDepositType.isPresent()) {
            final Message message = sender.sendMessageForOpenDeposit(depositDto);
            return convertToObj(message.getBody(), MessageResponseDto.class);
        }
        throw new ApplicationException(INCORRECT_DATA);
    }

    @NotNull
    @Override
    public MessageResponseDto updateDeposit(@NotNull DepositUpdaterMessageDto depositDto) {
        final Optional<Deposit> optionalDeposit =
                depositRepository.findById(depositDto.getDepositId());
        if (optionalDeposit.isPresent()) {
            final Message message = sender.sendMessageForUpdateDeposit(depositDto);
            return convertToObj(message.getBody(), MessageResponseDto.class);
        }
        throw new ApplicationException(DEPOSIT_NOT_FOUND);
    }
}
