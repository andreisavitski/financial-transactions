package eu.senla.financialtransactions.repository.impl;

import eu.senla.financialtransactions.entity.Activity;
import eu.senla.financialtransactions.mapper.ActivityRowMapper;
import eu.senla.financialtransactions.repository.JdbcTemplateActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JdbcTemplateActivityRepositoryImpl implements JdbcTemplateActivityRepository {

    private final JdbcTemplate jdbcTemplate;

    private final ActivityRowMapper activityRowMapper;

    @Override
    public List<Activity> findAll() {
        return jdbcTemplate.query("""
                        select
                            activ.id,
                            activ.name,
                            oper.id as oper_id,
                            oper.name as oper_name
                        from public.activity activ
                        join public.operator oper on activ.id = oper.activity_id
                        ;\040"""
                , activityRowMapper);
    }
}
