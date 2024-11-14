package eu.senla.financialtransactions.service.impl;

import eu.senla.financialtransactions.entity.Action;
import eu.senla.financialtransactions.entity.Operation;
import eu.senla.financialtransactions.repository.ActionRepository;
import eu.senla.financialtransactions.service.ActionService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ActionServiceImpl implements ActionService {

    private final ActionRepository actionRepository;

    @Override
    public void save(@NotNull Operation operation) {
        final Action action = new Action();
        action.setOperation(operation);
        actionRepository.save(action);
    }

    @NotNull
    @Override
    public List<Action> findActionByClientId(@NotNull Long clientId) {
        return actionRepository.findByClientId(clientId);
    }
}
