package eu.senla.financialtransactions.dto;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Data
@Builder
@Jacksonized
public class MessageResponseDto {

    private final String status;

    private final List<CardDto> data;

    private final List<Exception> exceptions;
}
