package eu.senla.financialtransactions.exception;

import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;

public interface AppError {

    @NotNull
    HttpStatus getStatus();

    @NotNull
    String getCode();
}
