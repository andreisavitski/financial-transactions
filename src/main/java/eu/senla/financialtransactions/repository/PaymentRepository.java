package eu.senla.financialtransactions.repository;

import eu.senla.financialtransactions.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaymentRepository extends JpaRepository<Payment, UUID> {
}
