package eu.senla.financialtransactions.controller.advice;

import eu.senla.financialtransactions.dto.exception.ErrorResponseDTO;
import eu.senla.financialtransactions.exception.ApplicationException;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestControllerAdvice
@Slf4j
public class ApplicationExceptionHandler {

    @NotNull
    @ExceptionHandler(ApplicationException.class)
    private ResponseEntity<ErrorResponseDTO> handleApplicationException(
            @NotNull ApplicationException applicationException) {
        return ResponseEntity.status(
                applicationException.getStatus()).body(
                new ErrorResponseDTO(applicationException.getStatus(), applicationException.getMessage())
        );
    }

    @NotNull
    @ExceptionHandler(Exception.class)
    private ResponseEntity<ErrorResponseDTO> handleBaseException(@NotNull Exception exception) {
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(
                new ErrorResponseDTO(INTERNAL_SERVER_ERROR, exception.getMessage())
        );
    }

    @NotNull
    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ResponseEntity<ErrorResponseDTO> handleValidationException(
            @NotNull MethodArgumentNotValidException validException) {
        return ResponseEntity.status(validException.getStatusCode()).body(
                new ErrorResponseDTO(validException.getStatusCode(), validException.getMessage())
        );
    }
}
