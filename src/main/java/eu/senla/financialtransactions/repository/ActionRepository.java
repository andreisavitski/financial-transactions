package eu.senla.financialtransactions.repository;

import eu.senla.financialtransactions.entity.Action;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ActionRepository extends MongoRepository<Action, Long> {

    @NotNull
    @Query(value = "{'operation.client._id': ?0}")
    List<Action> findByClientId(@NotNull Long clientId);
}
