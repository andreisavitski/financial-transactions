package eu.senla.financialtransactions.mapper;

import eu.senla.financialtransactions.entity.Activity;
import eu.senla.financialtransactions.entity.Operator;
import jakarta.validation.constraints.NotNull;
import lombok.SneakyThrows;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Component
public class ActivityRowMapper implements RowMapper<Activity> {

    @NotNull
    @Override
    @SneakyThrows
    public Activity mapRow(@NotNull ResultSet rs, int rowNum) {
        Activity activity = new Activity();
        activity.setId(rs.getLong("id"));
        activity.setName(rs.getString("name"));
        List<Operator> operators = new ArrayList<>();
        do {
            Operator operator = new Operator();
            operator.setId(rs.getLong("id"));
            operator.setName(rs.getString("name"));
            operators.add(operator);
        }
        while (rs.next() && rs.getLong("id") == activity.getId());
        activity.setOperators(operators);
        return activity;
    }
}
