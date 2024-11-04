package eu.senla.financialtransactions.repository;

import eu.senla.financialtransactions.entity.Transfer;
import eu.senla.financialtransactions.enums.Status;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.UUID;

public interface TransferRepository extends JpaRepository<Transfer, UUID> {

    void deleteByTransferStartDateTimeBeforeAndStatus(@NotNull LocalDateTime thresholdDate,
                                                      @NotNull Status status);
}
