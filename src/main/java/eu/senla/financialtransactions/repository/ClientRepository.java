package eu.senla.financialtransactions.repository;

import eu.senla.financialtransactions.entity.Client;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, Long> {

    @NotNull
    Optional<Client> findById(@NotNull UUID id);
}
