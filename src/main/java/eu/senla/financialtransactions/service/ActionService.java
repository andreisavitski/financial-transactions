package eu.senla.financialtransactions.service;

import eu.senla.financialtransactions.entity.Action;
import eu.senla.financialtransactions.entity.Operation;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public interface ActionService {

    void save(@NotNull Operation operation);

    @NotNull
    List<Action> findActionByClientId(@NotNull Long clientId);
}
