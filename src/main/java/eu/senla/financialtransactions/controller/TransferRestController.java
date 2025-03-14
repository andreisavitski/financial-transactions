package eu.senla.financialtransactions.controller;

import eu.senla.financialtransactions.dto.MessageResponseDto;
import eu.senla.financialtransactions.dto.TransferRequestDto;
import eu.senla.financialtransactions.dto.UuidDto;
import eu.senla.financialtransactions.service.TransferService;
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

    @PostMapping("/check")
    @PreAuthorize("hasAuthority(@permissionProvider.getPermissionForTransfer())")
    public UuidDto checkTransfer(
            @RequestBody TransferRequestDto transferCheckResponseDto) {
        return transferService.checkTransfer(transferCheckResponseDto);
    }

    @PostMapping("/execute")
    @PreAuthorize("hasAuthority(@permissionProvider.getPermissionForTransfer())")
    public MessageResponseDto executeTransfer(
            @RequestBody UuidDto uuidDto) {
        return transferService.executeTransfer(uuidDto);
    }
}
