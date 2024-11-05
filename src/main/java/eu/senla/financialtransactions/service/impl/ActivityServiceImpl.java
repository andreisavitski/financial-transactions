package eu.senla.financialtransactions.service.impl;

import eu.senla.financialtransactions.dto.ActivityDto;
import eu.senla.financialtransactions.mapper.ActivityMapper;
import eu.senla.financialtransactions.repository.JdbcTemplateActivityRepository;
import eu.senla.financialtransactions.service.ActivityService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ActivityServiceImpl implements ActivityService {

    private final JdbcTemplateActivityRepository jdbcTemplateActivityRepository;

    private final ActivityMapper activityMapper;

    @NotNull
    @Override
    public List<ActivityDto> findAll() {
        return jdbcTemplateActivityRepository.findAll().stream()
                .map(activityMapper::toDto)
                .toList();
    }
}
