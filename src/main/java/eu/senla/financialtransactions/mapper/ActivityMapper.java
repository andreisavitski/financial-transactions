package eu.senla.financialtransactions.mapper;

import eu.senla.financialtransactions.dto.ActivityDto;
import eu.senla.financialtransactions.entity.Activity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface ActivityMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "operators", target = "operators")
    ActivityDto toDto(Activity activity);
}
