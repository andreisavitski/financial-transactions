package eu.senla.financialtransactions.service;

import eu.senla.financialtransactions.dto.MessageResponseDto;
import eu.senla.financialtransactions.dto.PaymentRequestDto;
import eu.senla.financialtransactions.dto.UuidDto;
import jakarta.validation.constraints.NotNull;

public interface PaymentService {

    @NotNull
    UuidDto checkPayment(@NotNull PaymentRequestDto paymentRequestDto);

    @NotNull
    MessageResponseDto executePayment(@NotNull UuidDto uuidDto);
}
