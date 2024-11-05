package eu.senla.financialtransactions.service;

import eu.senla.financialtransactions.dto.ActivityDto;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public interface ActivityService {

    @NotNull
    List<ActivityDto> findAll();
}
