package eu.senla.financialtransactions.entity;

import eu.senla.financialtransactions.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.FetchType.LAZY;

@Getter
@Setter
@Entity
@Table(name = "payment")
public class Payment {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "cardId")
    private Long cardId;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "status")
    @Enumerated(STRING)
    private Status status;

    @Column(name = "payment_start_date_time")
    private LocalDateTime paymentStartDateTime;

    @Column(name = "payment_end_date_time")
    private LocalDateTime paymentEndDateTime;

    @Column(name = "operator_id")
    private Long operatorId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "client_id")
    private Client client;
}
