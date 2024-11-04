package eu.senla.financialtransactions.dto;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;

@Data
@Builder
@Jacksonized
public class OperatorRequestDto {

    private final Long clientId;

    private final Long operatorId;

    private final BigDecimal amount;
}
