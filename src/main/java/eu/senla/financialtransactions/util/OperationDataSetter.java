package eu.senla.financialtransactions.util;

import eu.senla.financialtransactions.entity.Client;
import eu.senla.financialtransactions.entity.Operation;
import jakarta.validation.constraints.NotNull;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.util.UUID;

import static eu.senla.financialtransactions.enums.Status.DONE;
import static eu.senla.financialtransactions.enums.Status.IN_PROGRESS;

@UtilityClass
public class OperationDataSetter {

    @NotNull
    public static void setDataAfterCheck(@NotNull Operation operation,
                                              @NotNull Client client) {
        operation.setClient(client);
        operation.setId(UUID.randomUUID());
        operation.setStatus(IN_PROGRESS);
        operation.setStartDateTime(LocalDateTime.now());
    }

    @NotNull
    public static void setDataAfterExecute(@NotNull Operation operation) {
        operation.setEndDateTime(LocalDateTime.now());
        operation.setStatus(DONE);
    }
}
