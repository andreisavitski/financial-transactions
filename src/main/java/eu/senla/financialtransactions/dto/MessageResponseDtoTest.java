package eu.senla.financialtransactions.dto;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Data
@Builder
@Jacksonized
public class MessageResponseDtoTest {

    private final String status;

    private final Object data;

    private final List<Exception> exceptions;
}
