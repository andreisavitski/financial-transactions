package eu.senla.financialtransactions.dto;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;

@Data
@Builder
@Jacksonized
public class TransferCheckRequestDto {

    private Long cardIdFrom;

    private Long cardIdTo;

    private BigDecimal amount;

    private Long clientId;
}
