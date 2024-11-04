package eu.senla.financialtransactions.service.impl;

import eu.senla.financialtransactions.dto.CardDto;
import eu.senla.financialtransactions.dto.MessageResponseDto;
import eu.senla.financialtransactions.dto.TransferRequestDto;
import eu.senla.financialtransactions.dto.UuidDto;
import eu.senla.financialtransactions.entity.Client;
import eu.senla.financialtransactions.entity.Transfer;
import eu.senla.financialtransactions.exception.ApplicationException;
import eu.senla.financialtransactions.mapper.TransferMapper;
import eu.senla.financialtransactions.repository.ClientRepository;
import eu.senla.financialtransactions.repository.TransferRepository;
import eu.senla.financialtransactions.service.CardService;
import eu.senla.financialtransactions.service.TransferService;
import eu.senla.financialtransactions.util.CardExtractor;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static eu.senla.financialtransactions.constant.AppStatusConstant.OK;
import static eu.senla.financialtransactions.enums.Status.DONE;
import static eu.senla.financialtransactions.enums.Status.IN_PROGRESS;
import static eu.senla.financialtransactions.exception.ApplicationError.*;

@Service
@RequiredArgsConstructor
public class TransferServiceImpl implements TransferService {

    private final ClientRepository clientRepository;

    private final TransferRepository transferRepository;

    private final TransferMapper transferMapper;

    private final CardService cardService;

    @NotNull
    @Override
    public UuidDto checkTransfer(
            @NotNull TransferRequestDto transferRequestDto) {
        final Client client = clientRepository.findById(transferRequestDto.getClientId())
                .orElseThrow(
                        () -> new ApplicationException(CLIENT_NOT_FOUND)
                );
        final Transfer transfer = transferMapper.toTransfer(transferRequestDto);
        validateDataForCheck(transfer);
        setDataToNewTransfer(transfer, client);
        transferRepository.save(transfer);
        return transferMapper.toUuidDto(transfer);
    }

    @NotNull
    @Override
    public MessageResponseDto executeTransfer(@NotNull UuidDto uuidDto) {
        final Transfer transfer = transferRepository.findById(uuidDto.getId()).orElseThrow(
                () -> new ApplicationException(TRANSFER_NOT_FOUND)
        );
        validateDataForExecute(transfer);
        final TransferRequestDto transferRequestDto =
                transferMapper.toTransferRequestDto(transfer);
        final MessageResponseDto messageResponseDto =
                cardService.executeTransferMoney(transferRequestDto);
        if (messageResponseDto.getStatus().equals(OK)) {
            transfer.setTransferEndDateTime(LocalDateTime.now());
            transfer.setStatus(DONE);
            transferRepository.save(transfer);
        }
        return messageResponseDto;
    }

    private void validateDataForCheck(@NotNull Transfer transfer) {
        final MessageResponseDto messageResponseDto = cardService.getClientCard(transfer.getClient().getId());
        final List<CardDto> card = messageResponseDto.getData();
        final CardDto cardDtoFrom = CardExtractor.getCardFromListByCardId(card, transfer.getCardIdFrom());
        CardExtractor.getCardFromListByCardId(card, transfer.getCardIdTo());
        if (cardDtoFrom.getAmount().compareTo(transfer.getAmount()) < 0) {
            throw new ApplicationException(NOT_ENOUGH_FUNDS);
        }
    }

    private void validateDataForExecute(@NotNull Transfer transfer) {
        if (transfer.getStatus().equals(DONE)) {
            throw new ApplicationException(ALREADY_COMPLETED);
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
