package eu.senla.financialtransactions.controller;

import eu.senla.financialtransactions.entity.Action;
import eu.senla.financialtransactions.service.ActionService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static eu.senla.financialtransactions.constant.AppConstants.CLIENT_ID;
import static eu.senla.financialtransactions.constant.AppConstants.DEFAULT_LIMIT;
import static eu.senla.financialtransactions.constant.AppConstants.DEFAULT_OFFSET;
import static eu.senla.financialtransactions.constant.AppConstants.ID;
import static eu.senla.financialtransactions.constant.AppConstants.LIMIT;
import static eu.senla.financialtransactions.constant.AppConstants.MAX_LIMIT;
import static eu.senla.financialtransactions.constant.AppConstants.MIN_LIMIT;
import static eu.senla.financialtransactions.constant.AppConstants.MIN_OFFSET;
import static eu.senla.financialtransactions.constant.AppConstants.OFFSET;

@RestController
@RequestMapping("/api/v1/action")
@RequiredArgsConstructor
public class ActionRestController {

    private final ActionService actionService;

    @NotNull
    @GetMapping("/client")
    public List<Action> getActionByClientId(
            @NotNull @RequestParam(value = CLIENT_ID) Long clientId) {
        return actionService.findActionByClientId(clientId);
    }

    @NotNull
    @GetMapping("/page")
    public Page<Action> getActionsByClientId(
            @NotNull @RequestParam(value = CLIENT_ID) Long clientId,
            @NotNull @RequestParam(value = OFFSET,
                    defaultValue = DEFAULT_OFFSET) @Min(MIN_OFFSET) Integer offset,
            @NotNull @RequestParam(value = LIMIT,
                    defaultValue = DEFAULT_LIMIT) @Min(MIN_LIMIT) @Max(MAX_LIMIT) Integer limit) {
        return actionService.findActionByClientId(clientId, PageRequest.of(offset, limit));
    }

    @NotNull
    @GetMapping("/id")
    public Action getActionId(@NotNull @RequestParam(value = ID) String id) {
        return actionService.findActionById(id);
    }
}
