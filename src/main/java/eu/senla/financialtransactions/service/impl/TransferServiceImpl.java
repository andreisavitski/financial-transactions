package eu.senla.financialtransactions.service.impl;

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
import eu.senla.financialtransactions.util.OperationDataSetter;
import eu.senla.financialtransactions.util.ResponseOperationHandler;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static eu.senla.financialtransactions.exception.ApplicationError.CLIENT_NOT_FOUND;
import static eu.senla.financialtransactions.exception.ApplicationError.TRANSFER_NOT_FOUND;
import static eu.senla.financialtransactions.util.OperationDataValidator.validateDataForCheck;
import static eu.senla.financialtransactions.util.OperationDataValidator.validateDataForExecute;

@Service
@RequiredArgsConstructor
public class TransferServiceImpl implements TransferService {

    private final CardService cardService;

    private final ClientRepository clientRepository;

    private final TransferRepository transferRepository;

    private final TransferMapper transferMapper;

    private final ResponseOperationHandler responseOperationHandler;

    @NotNull
    @Override
    public UuidDto checkTransfer(
            @NotNull TransferRequestDto transferRequestDto) {
        final Client client = clientRepository.findById(transferRequestDto.getClientId())
                .orElseThrow(() -> new ApplicationException(CLIENT_NOT_FOUND));
        final Transfer transfer = transferMapper.toTransfer(transferRequestDto);
        final MessageResponseDto messageResponseDto =
                cardService.getClientCard(transfer.getClient().getId());
        validateDataForCheck(transfer, messageResponseDto);
        OperationDataSetter.setDataAfterCheck(transfer, client);
        transferRepository.save(transfer);
        return transferMapper.toUuidDto(transfer);
    }

    @NotNull
    @Override
    public MessageResponseDto executeTransfer(@NotNull UuidDto uuidDto) {
        final Transfer transfer = transferRepository.findById(uuidDto.getId()).
                orElseThrow(() -> new ApplicationException(TRANSFER_NOT_FOUND));
        final MessageResponseDto messageResponseDto =
                cardService.getClientCard(transfer.getClient().getId());
        validateDataForExecute(transfer, messageResponseDto);
        final TransferRequestDto transferRequestDto =
                transferMapper.toTransferRequestDto(transfer);
        final MessageResponseDto messageResponseDtoAfterExecute =
                cardService.executeTransferMoney(transferRequestDto);
        return responseOperationHandler.saveData(messageResponseDtoAfterExecute, transfer);
    }
}
