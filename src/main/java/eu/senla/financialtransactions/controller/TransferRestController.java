package eu.senla.financialtransactions.controller;

import eu.senla.financialtransactions.dto.TransferRequestDto;
import eu.senla.financialtransactions.dto.MessageResponseDto;
import eu.senla.financialtransactions.dto.UuidDto;
import eu.senla.financialtransactions.service.TransferService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
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

    @NotNull
    @PostMapping("/check")
    @PreAuthorize("hasAuthority(@permissionProvider.getPermissionForTransfer())")
    public UuidDto checkTransfer(
            @RequestBody @NotNull TransferRequestDto transferCheckResponseDto) {
        return transferService.checkTransfer(transferCheckResponseDto);
    }

    @NotNull
    @PostMapping("/execute")
    @PreAuthorize("hasAuthority(@permissionProvider.getPermissionForTransfer())")
    public MessageResponseDto executeTransfer(
            @RequestBody @NotNull UuidDto uuidDto) {
        return transferService.executeTransfer(uuidDto);
    }
}
