package eu.senla.financialtransactions.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
public class DepositOpenerMessageDto {

    private final UUID depositTypeId;

    private final UUID cardId;

    private final UUID clientId;

    private final BigDecimal amount;

    private final Long termInMonths;

    private final String purpose;
}
