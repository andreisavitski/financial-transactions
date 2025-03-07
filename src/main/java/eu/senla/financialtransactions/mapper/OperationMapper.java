package eu.senla.financialtransactions.mapper;

import eu.senla.financialtransactions.dto.OperationDto;
import eu.senla.financialtransactions.entity.Operation;
import eu.senla.financialtransactions.entity.Payment;
import eu.senla.financialtransactions.entity.Transfer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface OperationMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "writeOffCardId", source = "writeOffCardId")
    @Mapping(target = "amount", source = "amount")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "startDateTime", source = "startDateTime")
    @Mapping(target = "endDateTime", source = "endDateTime")
    @Mapping(target = "clientId", source = "client.id")
    @Mapping(target = "operatorId", ignore = true)
    @Mapping(target = "targetCardId", ignore = true)
    OperationDto toDto(Operation operation);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "writeOffCardId", source = "writeOffCardId")
    @Mapping(target = "amount", source = "amount")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "startDateTime", source = "startDateTime")
    @Mapping(target = "endDateTime", source = "endDateTime")
    @Mapping(target = "clientId", source = "client.id")
    @Mapping(target = "operatorId", source = "operatorId")
    @Mapping(target = "targetCardId", ignore = true)
    OperationDto toDto(Payment payment);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "writeOffCardId", source = "writeOffCardId")
    @Mapping(target = "amount", source = "amount")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "startDateTime", source = "startDateTime")
    @Mapping(target = "endDateTime", source = "endDateTime")
    @Mapping(target = "clientId", source = "client.id")
    @Mapping(target = "targetCardId", source = "targetCardId")
    @Mapping(target = "operatorId", ignore = true)
    OperationDto toDto(Transfer transfer);

    default List<OperationDto> toDtoList(List<Operation> operations) {
        return operations.stream()
                .map(op -> switch (op) {
                    case Payment payment -> toDto(payment);
                    case Transfer transfer -> toDto(transfer);
                    default -> toDto(op);
                })
                .toList();
    }
}