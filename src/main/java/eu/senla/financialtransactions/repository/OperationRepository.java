package eu.senla.financialtransactions.repository;

import eu.senla.financialtransactions.dto.projection.OperationProjection;
import eu.senla.financialtransactions.entity.Operation;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface OperationRepository extends JpaRepository<Operation, UUID> {

    @NotNull
    @Query(value = """
            SELECT id, write_off_card_id, amount, status, start_date_time, end_date_time, client_id,
                   NULL AS operator_id, target_card_id, 'TRANSFER' AS operation_type
            FROM transfer
            UNION ALL
            SELECT id, write_off_card_id, amount, status, start_date_time, end_date_time, client_id,
                   operator_id, NULL AS target_card_id, 'PAYMENT' AS operation_type
            FROM payment
            """, nativeQuery = true)
    List<OperationProjection> findAllOperations();
}
