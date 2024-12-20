package eu.senla.financialtransactions.controller;

import eu.senla.financialtransactions.dto.MessageResponseDto;
import eu.senla.financialtransactions.dto.PaymentRequestDto;
import eu.senla.financialtransactions.dto.UuidDto;
import eu.senla.financialtransactions.service.PaymentService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payment")
@RequiredArgsConstructor
public class PaymentRestController {

    private final PaymentService paymentService;

    @NotNull
    @PostMapping("/check")
    @PreAuthorize("hasAuthority(@permissionProvider.getPermissionForPayment())")
    public UuidDto checkPayment(@NotNull @RequestBody PaymentRequestDto paymentRequestDto) {
        return paymentService.checkPayment(paymentRequestDto);
    }

    @NotNull
    @PostMapping("/execute")
    @PreAuthorize("hasAuthority(@permissionProvider.getPermissionForPayment())")
    public MessageResponseDto executePayment(@NotNull @RequestBody UuidDto uuidDto) {
        return paymentService.executePayment(uuidDto);
    }
}
