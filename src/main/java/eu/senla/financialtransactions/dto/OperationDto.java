package eu.senla.financialtransactions.dto;

import eu.senla.financialtransactions.enums.Status;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class OperationDto {

    private final UUID id;

    private final UUID writeOffCardId;

    private final BigDecimal amount;

    private final Status status;

    private final LocalDateTime startDateTime;

    private final LocalDateTime endDateTime;

    private final UUID clientId;

    private final UUID operatorId;

    private final UUID targetCardId;

    private final String operationType;
}