package eu.senla.financialtransactions.mapper;

import eu.senla.financialtransactions.dto.TransferCheckResponseDto;
import eu.senla.financialtransactions.dto.TransferCheckerRequestDto;
import eu.senla.financialtransactions.dto.TransferRequestMessage;
import eu.senla.financialtransactions.entity.Transfer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransferMapper {

    TransferCheckResponseDto toTransferCheckResponseDto(Transfer transfer);

    @Mapping(source = "clientId", target = "client.id")
    Transfer toTransfer(TransferCheckerRequestDto transferCheckerRequestDtoDto);

    TransferRequestMessage toTransferRequestMessage(Transfer transfer);
}
