package eu.senla.financial_transactions.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class ClientCardRequest {

    private Long id;
}
