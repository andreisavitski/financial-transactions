package eu.senla.financialtransactions.dto;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@Jacksonized
public class ActivityDto {

    private final UUID id;

    private final String name;

    private final List<OperatorDto> operators;
}
