package eu.senla.financialtransactions.dto;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Data
@Builder
@Jacksonized
public class ResponseMessage {

    private final String status;

    private Object data;

    private List<Exception> exceptions;
}
