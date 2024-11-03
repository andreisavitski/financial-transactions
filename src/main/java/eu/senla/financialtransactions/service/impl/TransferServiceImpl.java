package eu.senla.financialtransactions.service.impl;

import eu.senla.financialtransactions.dto.*;
import eu.senla.financialtransactions.entity.Client;
import eu.senla.financialtransactions.entity.Transfer;
import eu.senla.financialtransactions.exception.ApplicationException;
import eu.senla.financialtransactions.mapper.TransferMapper;
import eu.senla.financialtransactions.repository.ClientRepository;
import eu.senla.financialtransactions.repository.TransferRepository;
import eu.senla.financialtransactions.service.CardService;
import eu.senla.financialtransactions.service.TransferService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static eu.senla.financialtransactions.constant.AppStatusConstant.OK;
import static eu.senla.financialtransactions.enums.TransferStatus.DONE;
import static eu.senla.financialtransactions.enums.TransferStatus.IN_PROGRESS;
import static eu.senla.financialtransactions.exception.ApplicationError.*;

@Service
@RequiredArgsConstructor
public class TransferServiceImpl implements TransferService {

    private final ClientRepository clientRepository;

    private final TransferRepository transferRepository;

    private final TransferMapper transferMapper;

    private final CardService cardService;

    @Override
    @NotNull
    public TransferCheckResponseDto checkTransfer(@NotNull TransferCheckRequestDto transferCheckRequestDto) {
        final Transfer transfer = transferMapper.toTransfer(transferCheckRequestDto);
        final Client client = clientRepository.findById(transfer.getClient().getId()).orElseThrow(
                () -> new ApplicationException(CLIENT_NOT_FOUND)
        );
        validateDataForCheck(transfer);
        setDataToNewTransfer(transfer, client);
        transferRepository.save(transfer);
        return transferMapper.toTransferCheckResponseDto(transfer);
    }

    @Override
    @NotNull
    public TransferExecuteResponseDto executeTransfer(@NotNull TransferExecuteRequestDto transferExecuteRequestDto) {
        final Optional<Transfer> transferOptional = transferRepository.findById(transferExecuteRequestDto.getId());
        final Transfer transfer = transferOptional.orElseThrow(
                () -> new ApplicationException(TRANSFER_NOT_FOUND)
        );
        validateDataForExecute(transfer);
        final TransferExecuteResponseDto transferExecuteResponseDto = cardService.sendMessageForTransfer(
                transferMapper.toTransferCheckRequestMessageDto(transfer)
        );
        if (transferExecuteResponseDto.getStatus().equals(OK)) {
            transfer.setTransferEndDateTime(LocalDateTime.now());
            transfer.setStatus(DONE);
            transferRepository.save(transfer);
        }
        return transferExecuteResponseDto;
    }

    @NotNull
    private CardDto getCardFromListById(@NotNull List<CardDto> cards, @NotNull Long id) {
        return cards.stream()
                .filter(cardDto -> cardDto.getId().equals(id))
                .findFirst()
                .orElseThrow(
                        () -> new ApplicationException(CARD_NOT_FOUND)
                );
    }

    private void validateDataForCheck(@NotNull Transfer transfer) {
        final TransferExecuteResponseDto transferExecuteResponseDto = cardService.getClientCard(transfer.getClient().getId());
        final List<CardDto> card = transferExecuteResponseDto.getData();
        final CardDto cardDtoFrom = getCardFromListById(card, transfer.getCardIdFrom());
        getCardFromListById(card, transfer.getCardIdTo());
        if (cardDtoFrom.getAmount().compareTo(transfer.getAmount()) < 0) {
            throw new ApplicationException(NOT_ENOUGH_FUNDS);
        }
    }

    private void validateDataForExecute(@NotNull Transfer transfer) {
        if (transfer.getStatus().equals(DONE)) {
            throw new ApplicationException(TRANSFER_ALREADY_COMPLETED);
        }
        validateDataForCheck(transfer);
    }

    private void setDataToNewTransfer(@NotNull Transfer transfer, @NotNull Client client) {
        transfer.setClient(client);
        transfer.setId(UUID.randomUUID());
        transfer.setStatus(IN_PROGRESS);
        transfer.setTransferStartDateTime(LocalDateTime.now());
    }
}
