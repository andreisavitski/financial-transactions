package eu.senla.financialtransactions.mapper;

import eu.senla.financialtransactions.dto.OperationDto;
import eu.senla.financialtransactions.dto.projection.OperationProjection;
import org.mapstruct.Mapper;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface OperationMapper {

    OperationDto toDto(OperationProjection projection);

    List<OperationDto> toDtoList(List<OperationProjection> projections);
}