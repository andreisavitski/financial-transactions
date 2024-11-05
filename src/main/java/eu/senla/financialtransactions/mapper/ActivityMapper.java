package eu.senla.financialtransactions.mapper;

import eu.senla.financialtransactions.dto.ActivityDto;
import eu.senla.financialtransactions.entity.Activity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ActivityMapper {

    ActivityDto toDto(Activity activity);
}
