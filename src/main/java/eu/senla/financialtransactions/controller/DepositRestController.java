package eu.senla.financialtransactions.controller;

import eu.senla.financialtransactions.dto.DepositOpenerMessageDto;
import eu.senla.financialtransactions.dto.DepositUpdaterMessageDto;
import eu.senla.financialtransactions.dto.MessageResponseDto;
import eu.senla.financialtransactions.service.DepositService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/deposit")
public class DepositRestController {

    private final DepositService depositService;

    @PostMapping("/open")
    @NotNull
    @PreAuthorize("hasAuthority(@permissionProvider.permissionForDeposit)")
    public MessageResponseDto openDeposit(@Valid @RequestBody DepositOpenerMessageDto depositDto) {
        return depositService.openDeposit(depositDto);
    }

    @PutMapping("/update")
    @NotNull
    @PreAuthorize("hasAuthority(@permissionProvider.permissionForDeposit)")
    public MessageResponseDto updateDeposit(@Valid @RequestBody DepositUpdaterMessageDto depositDto) {
        return depositService.updateDeposit(depositDto);
    }
}
