package eu.senla.financialtransactions.repository;

import eu.senla.financialtransactions.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
