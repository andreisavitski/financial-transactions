package eu.senla.financialtransactions.entity;

import eu.senla.financialtransactions.enums.TransactionStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static jakarta.persistence.EnumType.STRING;

@Data
@Entity
@Table(name = "transfer")
public class Transfer {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "card_id_from")
    private Long cardIdFrom;

    @Column(name = "card_id_to")
    private Long cardIdTo;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "status")
    @Enumerated(STRING)
    private TransactionStatus status;

    @Column(name = "transfer_start_date_time")
    private LocalDateTime transferStartDateTime;

    @Column(name = "transfer_end_date_time")
    private LocalDateTime transferEndDateTime;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
}
