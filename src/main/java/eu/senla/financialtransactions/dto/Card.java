package eu.senla.financialtransactions.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Card {

    private Long id;

    private Long number;

    private BigDecimal amount;

    private Long clientId;
}
