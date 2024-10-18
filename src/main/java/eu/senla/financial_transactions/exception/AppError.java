package eu.senla.financial_transactions.exception;

import org.springframework.http.HttpStatus;

public interface AppError {
    HttpStatus getStatus();

    String getCode();
}
