package eu.senla.financialtransactions.mapper;

import eu.senla.financialtransactions.dto.TransferRequestDto;
import eu.senla.financialtransactions.dto.UuidDto;
import eu.senla.financialtransactions.entity.Transfer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface TransferMapper {

    @Mapping(source = "id", target = "id")
    UuidDto toUuidDto(Transfer transfer);

    @Mapping(target = "status", ignore = true)
    @Mapping(target = "startDateTime", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "endDateTime", ignore = true)
    @Mapping(source = "clientId", target = "client.id")
    @Mapping(source = "writeOffCardId", target = "writeOffCardId")
    @Mapping(source = "targetCardId", target = "targetCardId")
    @Mapping(source = "amount", target = "amount")
    Transfer toTransfer(TransferRequestDto transferRequestDto);

    @Mapping(source = "writeOffCardId", target = "writeOffCardId")
    @Mapping(source = "targetCardId", target = "targetCardId")
    @Mapping(source = "amount", target = "amount")
    @Mapping(source = "client.id", target = "clientId")
    TransferRequestDto toTransferRequestDto(Transfer transfer);
}
