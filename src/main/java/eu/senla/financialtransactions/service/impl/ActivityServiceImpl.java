package eu.senla.financialtransactions.service.impl;

import eu.senla.financialtransactions.dto.ActivityDto;
import eu.senla.financialtransactions.entity.Activity;
import eu.senla.financialtransactions.mapper.ActivityMapper;
import eu.senla.financialtransactions.repository.ActivityRepository;
import eu.senla.financialtransactions.service.ActivityService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ActivityServiceImpl implements ActivityService {

    private final ActivityRepository activityRepository;

    private final ActivityMapper activityMapper;

    @NotNull
    @Override
    public Page<ActivityDto> findAll(@NotNull PageRequest pageRequest) {
        final Page<Activity> activitiesPage = activityRepository.findAll(pageRequest);
        final List<ActivityDto> activitiesDtoList = activitiesPage.stream()
                .map(activityMapper::toDto)
                .toList();
        return new PageImpl<>(activitiesDtoList, pageRequest, activitiesPage.getTotalElements()
        );
    }
}
