package eu.senla.financialtransactions.service.impl;

import eu.senla.financialtransactions.dto.OperationDto;
import eu.senla.financialtransactions.dto.projection.OperationProjection;
import eu.senla.financialtransactions.mapper.OperationMapper;
import eu.senla.financialtransactions.repository.OperationRepository;
import eu.senla.financialtransactions.service.OperationService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OperationServiceImpl implements OperationService {

    private final OperationRepository operationRepository;

    private final OperationMapper operationMapper;

    @NotNull
    @Override
    public List<OperationDto> findAll() {
        final List<OperationProjection> operations = operationRepository.findAllOperations();
        return operationMapper.toDtoList(operations);
    }
}