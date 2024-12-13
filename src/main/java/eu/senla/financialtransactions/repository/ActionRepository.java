package eu.senla.financialtransactions.repository;

import eu.senla.financialtransactions.entity.Action;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ActionRepository extends MongoRepository<Action, Long> {

    @NotNull
    @Query(value = "{'operation.client._id': ?0 }")
    List<Action> findByClientId(@NotNull Long clientId);

    @NotNull
    @Query(value = "{'operation.client._id': ?0 }", sort = "{'operations.endDateTime': -1 }")
    Page<Action> findByClientId(@NotNull Long clientId, @NotNull Pageable pageable);

    @NotNull
    @Query(value = "{'id': ?0 }")
    Optional<Action> findById(@NotNull String id);
}
