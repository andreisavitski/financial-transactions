package eu.senla.financialtransactions.service;

import eu.senla.financialtransactions.dto.ActivityDto;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface ActivityService {

    @NotNull
    Page<ActivityDto> getAllActivities(@NotNull PageRequest pageRequest);
}
