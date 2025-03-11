package eu.senla.financialtransactions.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "deposit")
public class Deposit {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "termInMonths")
    private Long termInMonths;

    @Column(name = "purpose")
    private String purpose;
}
