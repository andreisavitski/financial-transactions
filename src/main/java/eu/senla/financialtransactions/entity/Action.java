package eu.senla.financialtransactions.entity;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document
public class Action {

    @Id
    private String id;

    private Operation operation;
}
