package eu.senla.financialtransactions.service.impl;

import eu.senla.financialtransactions.entity.Action;
import eu.senla.financialtransactions.entity.Operation;
import eu.senla.financialtransactions.exception.ApplicationException;
import eu.senla.financialtransactions.repository.ActionRepository;
import eu.senla.financialtransactions.service.ActionService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

import static eu.senla.financialtransactions.exception.ApplicationError.ACTION_NOT_FOUND;

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

    @NotNull
    @Override
    public Page<Action> findActionByClientId(@NotNull Long clientId,
                                             @NotNull PageRequest pageRequest) {
        final Page<Action> actionPage = actionRepository.findByClientId(clientId, pageRequest);
        final List<Action> actions = actionPage.stream().toList();
        return new PageImpl<>(actions, pageRequest, actionPage.getTotalElements());
    }

    @NotNull
    @Override
    public Action findActionById(@NotNull String id) {
        return actionRepository.findById(id).orElseThrow(
                () -> new ApplicationException(ACTION_NOT_FOUND));
    }
}
