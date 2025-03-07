package eu.senla.financialtransactions.dto.projection;

import eu.senla.financialtransactions.enums.Status;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public interface OperationProjection {

    UUID getId();

    UUID getWriteOffCardId();

    BigDecimal getAmount();

    Status getStatus();

    LocalDateTime getStartDateTime();

    LocalDateTime getEndDateTime();

    UUID getClientId();

    UUID getOperatorId();

    UUID getTargetCardId();

    String getOperationType();
}
