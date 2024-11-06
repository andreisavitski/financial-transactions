package eu.senla.financialtransactions.controller;

import eu.senla.financialtransactions.dto.ActivityDto;
import eu.senla.financialtransactions.enums.ActivitySort;
import eu.senla.financialtransactions.service.ActivityService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static eu.senla.financialtransactions.constant.AppConstants.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/activity")
@RequiredArgsConstructor
public class ActivityRestController {

    private final ActivityService activityService;

    @NotNull
    @GetMapping
    @PreAuthorize("hasAuthority(@permissionProvider.getPermissionForPayment())")
    public Page<ActivityDto> getAll(
            @NotNull @RequestParam(value = OFFSET,
                    defaultValue = DEFAULT_OFFSET) @Min(0) Integer offset,
            @NotNull @RequestParam(value = LIMIT,
                    defaultValue = DEFAULT_LIMIT) @Min(1) @Max(20) Integer limit,
            @NotNull @RequestParam(value = SORT) ActivitySort sort
    ) {
        return activityService.findAll(PageRequest.of(
                offset,
                limit,
                sort.getSortValue()
        ));
    }
}
