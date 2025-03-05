package eu.senla.financialtransactions.dto;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class ActionDto {

    private String id;

    private Object data;
}
