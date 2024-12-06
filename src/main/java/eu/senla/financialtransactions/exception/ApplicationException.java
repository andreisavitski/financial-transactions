package eu.senla.financialtransactions.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApplicationException extends RuntimeException {

    private final HttpStatus status;

    private final String code;

    public ApplicationException(AppError appError) {
        this(appError, appError.getCode());
    }

    public ApplicationException(AppError appError, String message) {
        super(message != null ? message : appError.getCode());
        this.status = appError.getStatus();
        this.code = appError.getCode();
    }
}
