package eu.senla.financialtransactions.service;

import eu.senla.financialtransactions.entity.Action;
import eu.senla.financialtransactions.entity.Operation;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface ActionService {

    void save(@NotNull Operation operation);

    @NotNull
    List<Action> findActionByClientId(@NotNull Long clientId);

    @NotNull
    Page<Action> findActionByClientId(@NotNull Long clientId, @NotNull PageRequest pageRequest);

    @NotNull
    Action findActionById(@NotNull String id);
}
