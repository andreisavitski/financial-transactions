package eu.senla.financialtransactions.dto;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;

@Data
@Builder
@Jacksonized
public class TransferCheckRequestDto {

    private final Long cardIdFrom;

    private final Long cardIdTo;

    private final BigDecimal amount;

    private final Long clientId;
}
