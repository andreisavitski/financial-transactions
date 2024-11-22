package eu.senla.financialtransactions.util;

import eu.senla.financialtransactions.dto.CardDto;
import eu.senla.financialtransactions.dto.MessageResponseDto;
import eu.senla.financialtransactions.entity.Operation;
import eu.senla.financialtransactions.exception.ApplicationException;
import jakarta.validation.constraints.NotNull;
import lombok.experimental.UtilityClass;

import java.util.List;

import static eu.senla.financialtransactions.constant.AppConstants.MINIMUM_TRANSFER_THRESHOLD;
import static eu.senla.financialtransactions.enums.Status.DONE;
import static eu.senla.financialtransactions.exception.ApplicationError.ALREADY_COMPLETED;
import static eu.senla.financialtransactions.exception.ApplicationError.NOT_ENOUGH_FUNDS;

@UtilityClass
public class OperationDataValidator {

    public static void validateDataForCheck(@NotNull Operation operation,
                                            @NotNull MessageResponseDto messageResponseDto) {
        final Object data = messageResponseDto.getData();
        final List<CardDto> cards = MessageConverter.convertToListObjects(data, CardDto.class);
        final CardDto writeOffCardDto =
                CardExtractor.getCardFromListByCardId(cards, operation.getWriteOffCardId());
        if (writeOffCardDto.getAmount().compareTo(operation.getAmount()) < MINIMUM_TRANSFER_THRESHOLD) {
            throw new ApplicationException(NOT_ENOUGH_FUNDS);
        }

    }

    public static void validateDataForExecute(@NotNull Operation operation,
                                              @NotNull MessageResponseDto messageResponseDto) {
        if (operation.getStatus().equals(DONE)) {
            throw new ApplicationException(ALREADY_COMPLETED);
        }
        validateDataForCheck(operation, messageResponseDto);
    }
}
