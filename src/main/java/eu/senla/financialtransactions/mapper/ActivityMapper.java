package eu.senla.financialtransactions.mapper;

import eu.senla.financialtransactions.dto.ActivityDto;
import eu.senla.financialtransactions.entity.Activity;
import org.mapstruct.Mapper;

import static eu.senla.financialtransactions.constant.AppConstants.SPRING;

@Mapper(componentModel = SPRING)
public interface ActivityMapper {

    ActivityDto toDto(Activity activity);
}
