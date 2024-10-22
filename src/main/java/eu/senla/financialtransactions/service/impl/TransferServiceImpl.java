package eu.senla.financialtransactions.service.impl;

import eu.senla.financialtransactions.dto.Card;
import eu.senla.financialtransactions.dto.TransferCheckResponseDto;
import eu.senla.financialtransactions.dto.TransferCheckerRequestDto;
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

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

import static eu.senla.financialtransactions.enums.TransactionStatus.DONE;
import static eu.senla.financialtransactions.enums.TransactionStatus.IN_PROGRESS;
import static eu.senla.financialtransactions.exception.ApplicationError.*;

@Service
@RequiredArgsConstructor
public class TransferServiceImpl implements TransferService {

    private final ClientRepository clientRepository;

    private final TransferRepository transferRepository;

    private final TransferMapper transferMapper;

    private final CardService cardService;

    @Override
    public TransferCheckResponseDto checkTransfer(TransferCheckerRequestDto transferCheckerRequestDto)
            throws InterruptedException, TimeoutException, IOException {
        Transfer transfer = transferMapper.toTransfer(transferCheckerRequestDto);
        Client client = clientRepository.findById(transfer.getClient().getId()).orElseThrow(
                () -> new ApplicationException(ApplicationError.CLIENT_NOT_FOUND)
        );
        Transfer transferAfterCheck = checkDataForTransfer(transfer);
        transferAfterCheck.setClient(client);
        transferAfterCheck.setId(UUID.randomUUID());
        transferAfterCheck.setStatus(IN_PROGRESS);
        transferAfterCheck.setTransferStartDateTime(LocalDateTime.now());
        transferRepository.save(transferAfterCheck);
        return transferMapper.toTransferCheckResponseDto(transferAfterCheck);
    }

    @Override
    public void executeTransfer(TransferExecuteRequestDto transferExecuteRequestDto)
            throws IOException, InterruptedException, TimeoutException {
        Optional<Transfer> transfer = transferRepository.findById(transferExecuteRequestDto.getId());
        if (transfer.isPresent() && transfer.get().getStatus().equals(DONE)) {
            throw new ApplicationException(TRANSFER_NOT_COMPLETED);
        }
        Transfer transferAfterCheck = checkDataForTransfer(transfer.orElseThrow());
        transferMapper.toTransferRequestMessage(transferAfterCheck);
        boolean responseMessage = cardService.sendMessageToTransfer(
                transferMapper.toTransferRequestMessage(transferAfterCheck)
        );
        if (!responseMessage) {
            throw new ApplicationException(TRANSFER_NOT_COMPLETED);
        }
        transferAfterCheck.setTransferEndDateTime(LocalDateTime.now());
        transferAfterCheck.setStatus(DONE);
        transferRepository.save(transfer.get());
    }

    private Card getCardFromListById(List<Card> cards, Long id) {
        return cards.stream()
                .filter(card -> card.getId().equals(id))
                .findFirst()
                .orElseThrow(
                        () -> new ApplicationException(CARD_NOT_FOUND)
                );
    }

    private Transfer checkDataForTransfer(Transfer transfer)
            throws IOException, InterruptedException, TimeoutException {
        List<Card> cards = cardService.getClientCard(transfer.getClient().getId());
        Card cardFrom = getCardFromListById(cards, transfer.getCardIdFrom());
        getCardFromListById(cards, transfer.getCardIdTo());
        if (cardFrom.getAmount().compareTo(transfer.getAmount()) < 0) {
            throw new ApplicationException(NOT_ENOUGH_FUNDS);
        }
        return transfer;
    }
}
