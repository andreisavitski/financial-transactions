package eu.senla.financialtransactions.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name = "client")
@Entity
public class Client {

    @Id
    @Column(name = "id")
    private Long id;
}
