package eu.senla.financialtransactions.dto;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;

@Data
@Builder
@Jacksonized
public class DepositOpenerMessageDto {

    private final UUID depositTypeId;

    private final Long cardId;

    private final Long clientId;
}
