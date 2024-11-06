package eu.senla.financialtransactions.repository;

import eu.senla.financialtransactions.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
}
