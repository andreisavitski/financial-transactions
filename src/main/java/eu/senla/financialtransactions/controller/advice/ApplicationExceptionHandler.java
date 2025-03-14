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
import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.status;

@RestControllerAdvice
@Slf4j
public class ApplicationExceptionHandler {

    @ExceptionHandler(ApplicationException.class)
    private ResponseEntity<ErrorResponseDTO> handleApplicationException(
            @NotNull ApplicationException applicationException) {
        return status(
                applicationException.getStatus()).body(
                new ErrorResponseDTO(applicationException.getStatus(),
                        applicationException.getMessage())
        );
    }

    @ExceptionHandler(Exception.class)
    private ResponseEntity<ErrorResponseDTO> handleBaseException(@NotNull Exception exception) {
        return status(INTERNAL_SERVER_ERROR).body(
                new ErrorResponseDTO(INTERNAL_SERVER_ERROR, exception.getMessage())
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ResponseEntity<?> handleValidationException() {
        return badRequest().build();
    }
}
