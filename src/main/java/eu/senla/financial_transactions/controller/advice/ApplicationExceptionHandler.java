package eu.senla.financial_transactions.controller.advice;

import eu.senla.financial_transactions.dto.exception.ErrorResponseDTO;
import eu.senla.financial_transactions.exception.ApplicationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestControllerAdvice
@Slf4j
public class ApplicationExceptionHandler {
    @ExceptionHandler(ApplicationException.class)
    private ResponseEntity<ErrorResponseDTO> handleApplicationException(ApplicationException applicationException) {
        return ResponseEntity.status(
                applicationException.getStatus()).body(
                new ErrorResponseDTO(applicationException.getStatus(), applicationException.getMessage())
        );
    }

    @ExceptionHandler(Exception.class)
    private ResponseEntity<ErrorResponseDTO> handleBaseException(Exception exception) {
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(
                new ErrorResponseDTO(INTERNAL_SERVER_ERROR, exception.getMessage())
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ResponseEntity<ErrorResponseDTO> handleValidationException(MethodArgumentNotValidException validException) {
        return ResponseEntity.status(validException.getStatusCode()).body(
                new ErrorResponseDTO(validException.getStatusCode(), validException.getMessage())
        );
    }
}
