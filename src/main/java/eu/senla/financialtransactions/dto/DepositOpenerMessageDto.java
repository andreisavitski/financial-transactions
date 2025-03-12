package eu.senla.financialtransactions.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

import static eu.senla.financialtransactions.constant.AppConstants.MAX_AMOUNT_FOR_DEPOSIT;
import static eu.senla.financialtransactions.constant.AppConstants.MAX_NUMBER_OF_CHARACTER;
import static eu.senla.financialtransactions.constant.AppConstants.MAX_TERM_IN_MONTHS_FOR_DEPOSIT;
import static eu.senla.financialtransactions.constant.AppConstants.MIN_AMOUNT_FOR_DEPOSIT;
import static eu.senla.financialtransactions.constant.AppConstants.MIN_NUMBER_OF_CHARACTER;
import static eu.senla.financialtransactions.constant.AppConstants.MIN_TERM_IN_MONTHS_FOR_DEPOSIT;

@Data
@Builder
public class DepositOpenerMessageDto {

    private final UUID depositTypeId;

    private final UUID cardId;

    private final UUID clientId;

    @Min(MIN_AMOUNT_FOR_DEPOSIT)
    @Max(MAX_AMOUNT_FOR_DEPOSIT)
    private final BigDecimal amount;

    @Min(MIN_TERM_IN_MONTHS_FOR_DEPOSIT)
    @Max(MAX_TERM_IN_MONTHS_FOR_DEPOSIT)
    private final Long termInMonths;

    @NotBlank
    @Size(min = MIN_NUMBER_OF_CHARACTER, max = MAX_NUMBER_OF_CHARACTER)
    private final String purpose;
}
