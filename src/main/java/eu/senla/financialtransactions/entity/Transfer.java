package eu.senla.financialtransactions.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "transfer")
public class Transfer extends Operation {

    @Column(name = "top_up_card_id")
    private Long targetCardId;
}
