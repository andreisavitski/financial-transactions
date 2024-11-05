package eu.senla.financialtransactions.dto;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;

@Data
@Builder
@Jacksonized
public class TransferRequestDto {

    private final Long writeOffCardId;

    private final Long topUpCardId;

    private final BigDecimal amount;

    private final Long clientId;
}
