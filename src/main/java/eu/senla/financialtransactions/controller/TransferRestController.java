package eu.senla.financialtransactions.controller;

import eu.senla.financialtransactions.dto.TransferCheckRequestDto;
import eu.senla.financialtransactions.dto.TransferCheckResponseDto;
import eu.senla.financialtransactions.dto.TransferExecuteRequestDto;
import eu.senla.financialtransactions.service.TransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/transfer")
public class TransferRestController {

    private final TransferService transferService;

    @PostMapping("/check")
    @PreAuthorize("hasAuthority(@permissionProvider.getPermissionForTransfer())")
    public TransferCheckResponseDto checkTransfer(
            @RequestBody TransferCheckRequestDto transferCheckResponseDto) {
        return transferService.checkTransfer(transferCheckResponseDto);
    }

    @PostMapping("/execute")
    @PreAuthorize("hasAuthority(@permissionProvider.getPermissionForTransfer())")
    public void executeTransfer(
            @RequestBody TransferExecuteRequestDto transferExecuteRequestDto) {
        transferService.executeTransfer(transferExecuteRequestDto);
    }
}
