package eu.senla.financialtransactions.controller;

import eu.senla.financialtransactions.dto.TransferCheckResponseDto;
import eu.senla.financialtransactions.dto.TransferCheckerRequestDto;
import eu.senla.financialtransactions.dto.TransferExecuteRequestDto;
import eu.senla.financialtransactions.service.TransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/transfer")
public class TransferRestController {

    private final TransferService transferService;

    @PostMapping("/check")
    @PreAuthorize("hasAuthority('transferMoneyBetweenYourAccounts')")
    public TransferCheckResponseDto checkTransfer(
            @RequestBody TransferCheckerRequestDto transferCheckResponseDto)
            throws IOException, InterruptedException, TimeoutException {
        return transferService.checkTransfer(transferCheckResponseDto);
    }

    @PostMapping("/execute")
    @PreAuthorize("hasAuthority('transferMoneyBetweenYourAccounts')")
    public void executeTransfer(
            @RequestBody TransferExecuteRequestDto transferExecuteRequestDto)
            throws IOException, InterruptedException, TimeoutException {
        transferService.executeTransfer(transferExecuteRequestDto);
    }
}
