package eu.senla.financialtransactions.controller;

import eu.senla.financialtransactions.dto.TransferExecuteResponseDto;
import eu.senla.financialtransactions.dto.TransferCheckRequestDto;
import eu.senla.financialtransactions.dto.TransferCheckResponseDto;
import eu.senla.financialtransactions.dto.TransferExecuteRequestDto;
import eu.senla.financialtransactions.service.TransferService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/transfer")
public class TransferRestController {

    private final TransferService transferService;

    @NotNull
    @PostMapping("/check")
//    @PreAuthorize("hasAuthority(@permissionProvider.getPermissionForTransfer())")
//    @PreAuthorize("hasAuthority('transferMoneyBetweenYourAccounts')")
    public TransferCheckResponseDto checkTransfer(
            @RequestBody @NotNull TransferCheckRequestDto transferCheckResponseDto) {
        return transferService.checkTransfer(transferCheckResponseDto);
    }

    @NotNull
    @PostMapping("/execute")
//    @PreAuthorize("hasAuthority(@permissionProvider.getPermissionForTransfer())")
//    @PreAuthorize("hasAuthority('transferMoneyBetweenYourAccounts')")
    public TransferExecuteResponseDto executeTransfer(
            @RequestBody @NotNull TransferExecuteRequestDto transferExecuteRequestDto) {
        return transferService.executeTransfer(transferExecuteRequestDto);
    }
}
