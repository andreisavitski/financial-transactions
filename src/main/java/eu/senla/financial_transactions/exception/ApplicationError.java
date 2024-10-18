package eu.senla.financial_transactions.exception;

import org.springframework.http.HttpStatus;

import java.util.function.Supplier;

import static org.springframework.http.HttpStatus.FORBIDDEN;

public enum ApplicationError implements AppError, Supplier<ApplicationException> {

    NO_ACCESS(FORBIDDEN, "No access");

    private final HttpStatus status;
    private final String code;

    ApplicationError(HttpStatus status, String code) {
        this.status = status;
        this.code = code;
    }

    @Override
    public ApplicationException get() {
        return new ApplicationException(this);
    }

    @Override
    public final HttpStatus getStatus() {
        return status;
    }

    @Override
    public final String getCode() {
        return code;
    }
}
