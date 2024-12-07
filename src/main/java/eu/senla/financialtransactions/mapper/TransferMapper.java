package eu.senla.financialtransactions.mapper;

import eu.senla.financialtransactions.dto.TransferRequestDto;
import eu.senla.financialtransactions.dto.UuidDto;
import eu.senla.financialtransactions.entity.Transfer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static eu.senla.financialtransactions.constant.AppConstants.SPRING;

@Mapper(componentModel = SPRING)
public interface TransferMapper {

    UuidDto toUuidDto(Transfer transfer);

    @Mapping(source = "clientId", target = "client.id")
    Transfer toTransfer(TransferRequestDto transferRequestDto);

    TransferRequestDto toTransferRequestDto(Transfer transfer);
}
