package eu.senla.financialtransactions.controller;

import eu.senla.financialtransactions.dto.ActivityDto;
import eu.senla.financialtransactions.enums.ActivitySort;
import eu.senla.financialtransactions.service.ActivityService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static eu.senla.financialtransactions.constant.AppConstants.DEFAULT_LIMIT;
import static eu.senla.financialtransactions.constant.AppConstants.DEFAULT_OFFSET;
import static eu.senla.financialtransactions.constant.AppConstants.LIMIT;
import static eu.senla.financialtransactions.constant.AppConstants.MAX_LIMIT;
import static eu.senla.financialtransactions.constant.AppConstants.MIN_LIMIT;
import static eu.senla.financialtransactions.constant.AppConstants.MIN_OFFSET;
import static eu.senla.financialtransactions.constant.AppConstants.OFFSET;
import static eu.senla.financialtransactions.constant.AppConstants.SORT;

@RestController
@RequestMapping("/api/v1/activity")
@RequiredArgsConstructor
public class ActivityRestController {

    private final ActivityService activityService;

    @GetMapping
    @PreAuthorize("hasAuthority(@permissionProvider.getPermissionForPayment())")
    public Page<ActivityDto> getAllActivities(
            @RequestParam(value = OFFSET,
                    defaultValue = DEFAULT_OFFSET) @Min(MIN_OFFSET) Integer offset,
            @RequestParam(value = LIMIT,
                    defaultValue = DEFAULT_LIMIT) @Min(MIN_LIMIT) @Max(MAX_LIMIT) Integer limit,
            @RequestParam(value = SORT) ActivitySort sort) {
        return activityService.getAllActivities(PageRequest.of(
                offset,
                limit,
                sort.getSortValue()
        ));
    }
}
