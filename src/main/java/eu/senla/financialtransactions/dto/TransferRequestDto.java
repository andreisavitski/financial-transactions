package eu.senla.financialtransactions.dto;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@Jacksonized
public class TransferRequestDto {

    private final UUID writeOffCardId;

    private final UUID targetCardId;

    private final BigDecimal amount;

    private final UUID clientId;
}
