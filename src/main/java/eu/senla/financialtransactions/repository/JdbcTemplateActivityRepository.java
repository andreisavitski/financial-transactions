package eu.senla.financialtransactions.repository;

import eu.senla.financialtransactions.entity.Activity;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public interface JdbcTemplateActivityRepository {

    @NotNull
    List<Activity> findAll();
}
