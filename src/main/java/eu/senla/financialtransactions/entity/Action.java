package eu.senla.financialtransactions.entity;

import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Action {

    @Id
    private String id;

    private Operation operation;
}
