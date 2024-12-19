package eu.senla.financialtransactions.entity;

import eu.senla.financialtransactions.enums.Status;
import jakarta.persistence.Column;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static jakarta.persistence.EnumType.STRING;

@Getter
@Setter
@MappedSuperclass
public class Operation {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "write_off_card_id")
    private UUID writeOffCardId;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "status")
    @Enumerated(STRING)
    private Status status;

    @Column(name = "start_date_time")
    private LocalDateTime startDateTime;

    @Column(name = "end_date_time")
    private LocalDateTime endDateTime;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
}
