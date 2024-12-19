package eu.senla.financialtransactions.exception;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.function.Supplier;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Getter
@AllArgsConstructor
public enum ApplicationError implements AppError, Supplier<ApplicationException> {

    NOT_ENOUGH_FUNDS(BAD_REQUEST, "Not enough funds on the card"),

    CARD_NOT_FOUND(NOT_FOUND, "The card does not exist"),

    CLIENT_NOT_FOUND(NOT_FOUND, "Client not found"),

    TRANSFER_NOT_FOUND(NOT_FOUND, "Transfer not found"),

    DEPOSIT_NOT_FOUND(NOT_FOUND, "Deposit not found"),

    ACTION_NOT_FOUND(NOT_FOUND, "Action not found"),

    INCORRECT_DATA(BAD_REQUEST, "Incorrect data"),

    ALREADY_COMPLETED(BAD_REQUEST, "Already completed");

    private final HttpStatus status;

    private final String code;

    @NotNull
    @Override
    public ApplicationException get() {
        return new ApplicationException(this);
    }
}
