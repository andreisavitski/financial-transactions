package eu.senla.financialtransactions.repository;

import eu.senla.financialtransactions.entity.DepositType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DepositTypeRepository extends JpaRepository<DepositType, UUID> {
}
