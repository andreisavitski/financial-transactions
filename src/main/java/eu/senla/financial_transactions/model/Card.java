package eu.senla.financial_transactions.model;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;

@Data
@Builder
@Jacksonized
public class Card {

    private Long id;

    private Long number;

    private BigDecimal amount;

    private Long clientId;
}
