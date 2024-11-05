package eu.senla.financialtransactions.dto;

import eu.senla.financialtransactions.entity.Operator;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Data
@Builder
@Jacksonized
public class ActivityDto {

    private final Long id;

    private final String name;

    private final List<Operator> operators;
}
