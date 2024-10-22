package eu.senla.financialtransactions.exception;

import org.springframework.http.HttpStatus;

public interface AppError {

    HttpStatus getStatus();

    String getCode();
}
