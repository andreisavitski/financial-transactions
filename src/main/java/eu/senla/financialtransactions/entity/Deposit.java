package eu.senla.financialtransactions.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "deposit")
public class Deposit {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "deposit_opening_date")
    private LocalDate depositOpeningDate;

    @Column(name = "deposit_closing_date")
    private LocalDate depositClosingDate;

    @Column(name = "amount")
    private BigDecimal amount;

    @Transient
    private Long term;

    @Column(name = "purpose")
    private String purpose;

    public Long getTerm() {
        final Period period = Period.between(depositOpeningDate, depositClosingDate);
        return (long) period.getMonths();
    }
}
