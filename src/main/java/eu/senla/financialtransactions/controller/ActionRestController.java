package eu.senla.financialtransactions.controller;

import eu.senla.financialtransactions.entity.Action;
import eu.senla.financialtransactions.service.ActionService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static eu.senla.financialtransactions.constant.AppConstants.CLIENT_ID;

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
}
