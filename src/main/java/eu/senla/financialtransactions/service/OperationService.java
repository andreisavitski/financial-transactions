package eu.senla.financialtransactions.service;

import eu.senla.financialtransactions.dto.OperationDto;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public interface OperationService {

    @NotNull
    List<OperationDto> findAll();
}