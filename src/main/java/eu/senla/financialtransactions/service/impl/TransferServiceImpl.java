package eu.senla.financialtransactions.service.impl;

import eu.senla.financialtransactions.dto.Card;
import eu.senla.financialtransactions.dto.TransferCheckRequestDto;
import eu.senla.financialtransactions.dto.TransferCheckResponseDto;
import eu.senla.financialtransactions.dto.TransferExecuteRequestDto;
import eu.senla.financialtransactions.entity.Client;
import eu.senla.financialtransactions.entity.Transfer;
import eu.senla.financialtransactions.exception.ApplicationError;
import eu.senla.financialtransactions.exception.ApplicationException;
import eu.senla.financialtransactions.mapper.TransferMapper;
import eu.senla.financialtransactions.repository.ClientRepository;
import eu.senla.financialtransactions.repository.TransferRepository;
import eu.senla.financialtransactions.service.CardService;
import eu.senla.financialtransactions.service.TransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static eu.senla.financialtransactions.enums.TransferStatus.DONE;
import static eu.senla.financialtransactions.enums.TransferStatus.IN_PROGRESS;
import static eu.senla.financialtransactions.exception.ApplicationError.*;
import static org.springframework.http.HttpStatus.OK;

@Service
@RequiredArgsConstructor
public class TransferServiceImpl implements TransferService {

    private final ClientRepository clientRepository;

    private final TransferRepository transferRepository;

    private final TransferMapper transferMapper;

    private final CardService cardService;

    @Override
    public TransferCheckResponseDto checkTransfer(TransferCheckRequestDto transferCheckRequestDto) {
        Transfer transfer = transferMapper.toTransfer(transferCheckRequestDto);
        Client client = clientRepository.findById(transfer.getClient().getId()).orElseThrow(
                () -> new ApplicationException(ApplicationError.CLIENT_NOT_FOUND)
        );
        validateDataForCheck(transfer);
        setDataToNewTransfer(transfer, client);
        transferRepository.save(transfer);
        return transferMapper.toTransferCheckResponseDto(transfer);
    }

    @Override
    public void executeTransfer(TransferExecuteRequestDto transferExecuteRequestDto) {
        Optional<Transfer> transferOptional = transferRepository.findById(transferExecuteRequestDto.getId());
        Transfer transfer = transferOptional.orElseThrow(
                () -> new ApplicationException(TRANSFER_NOT_FOUND)
        );
        validateDataForExecute(transfer);
        if (cardService.sendMessageForTransfer(
                transferMapper.toTransferRequestMessage(transfer)) != OK) {
            throw new ApplicationException(TRANSFER_NOT_COMPLETED);
        }
        transfer.setTransferEndDateTime(LocalDateTime.now());
        transfer.setStatus(DONE);
        transferRepository.save(transfer);
    }

    private Card getCardFromListById(List<Card> cards, Long id) {
        return cards.stream()
                .filter(card -> card.getId().equals(id))
                .findFirst()
                .orElseThrow(
                        () -> new ApplicationException(CARD_NOT_FOUND)
                );
    }

    private void validateDataForCheck(Transfer transfer) {
        List<Card> cards = cardService.getClientCard(transfer.getClient().getId());
        Card cardFrom = getCardFromListById(cards, transfer.getCardIdFrom());
        getCardFromListById(cards, transfer.getCardIdTo());
        if (cardFrom.getAmount().compareTo(transfer.getAmount()) < 0) {
            throw new ApplicationException(NOT_ENOUGH_FUNDS);
        }
    }

    private void validateDataForExecute(Transfer transfer) {
        if (transfer.getStatus().equals(DONE)) {
            throw new ApplicationException(TRANSFER_ALREADY_COMPLETED);
        }
        validateDataForCheck(transfer);
    }

    private void setDataToNewTransfer(Transfer transfer, Client client) {
        transfer.setClient(client);
        transfer.setId(UUID.randomUUID());
        transfer.setStatus(IN_PROGRESS);
        transfer.setTransferStartDateTime(LocalDateTime.now());
    }
}
