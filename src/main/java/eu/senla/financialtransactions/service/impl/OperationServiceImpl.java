package eu.senla.financialtransactions.service.impl;

import eu.senla.financialtransactions.dto.OperationDto;
import eu.senla.financialtransactions.entity.Operation;
import eu.senla.financialtransactions.entity.Payment;
import eu.senla.financialtransactions.entity.Transfer;
import eu.senla.financialtransactions.mapper.OperationMapper;
import eu.senla.financialtransactions.repository.PaymentRepository;
import eu.senla.financialtransactions.repository.TransferRepository;
import eu.senla.financialtransactions.service.OperationService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OperationServiceImpl implements OperationService {

    private final PaymentRepository paymentRepository;

    private final TransferRepository transferRepository;

    private final OperationMapper operationMapper;

    @NotNull
    @Override
    public List<OperationDto> findAll() {
        final List<Payment> payments = paymentRepository.findAll();
        final List<Transfer> transfers = transferRepository.findAll();
        final List<Operation> operations = new ArrayList<>();
        operations.addAll(payments);
        operations.addAll(transfers);
        return operationMapper.toDtoList(operations);
    }
}