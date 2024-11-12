package eu.senla.financialtransactions.repository;

import eu.senla.financialtransactions.entity.Payment;
import eu.senla.financialtransactions.enums.Status;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.UUID;

public interface PaymentRepository extends JpaRepository<Payment, UUID> {

    void deleteByStartDateTimeBeforeAndStatus(@NotNull LocalDateTime thresholdDate,
                                              @NotNull Status status);
}
