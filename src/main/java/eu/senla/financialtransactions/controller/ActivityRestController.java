package eu.senla.financialtransactions.controller;

import eu.senla.financialtransactions.dto.ActivityDto;
import eu.senla.financialtransactions.service.ActivityService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/activity")
@RequiredArgsConstructor
public class ActivityRestController {

    private final ActivityService activityService;

    @NotNull
    @GetMapping("/get")
    @PreAuthorize("hasAuthority(@permissionProvider.getPermissionForPayment())")
    public List<ActivityDto> getActivities() {
        return activityService.findAll();
    }
}
