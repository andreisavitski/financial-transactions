package eu.senla.financialtransactions.dto;

import eu.senla.financialtransactions.exception.ApplicationException;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Data
@Builder
@Jacksonized
public class ClientCardResponseMessage {

    private List<Card> cards;

    private ApplicationException applicationException;
}
