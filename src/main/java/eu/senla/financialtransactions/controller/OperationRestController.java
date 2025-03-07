package eu.senla.financialtransactions.controller;

import eu.senla.financialtransactions.dto.OperationDto;
import eu.senla.financialtransactions.service.OperationService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/operation")
@RequiredArgsConstructor
public class OperationRestController {

    private final OperationService operationService;

    @NotNull
    @GetMapping("/all")
    public List<OperationDto> getOperations() {
        return operationService.findAll();
    }
}