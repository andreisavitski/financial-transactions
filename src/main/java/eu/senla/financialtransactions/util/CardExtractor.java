package eu.senla.financialtransactions.util;

import eu.senla.financialtransactions.dto.CardDto;
import eu.senla.financialtransactions.exception.ApplicationException;
import jakarta.validation.constraints.NotNull;

import java.util.List;

import static eu.senla.financialtransactions.exception.ApplicationError.CARD_NOT_FOUND;

public class CardExtractor {

    @NotNull
    public static CardDto getCardFromListByCardId(@NotNull List<CardDto> cards, @NotNull Long cardId) {
        return cards.stream()
                .filter(cardDto -> cardDto.getId().equals(cardId))
                .findFirst()
                .orElseThrow(
                        () -> new ApplicationException(CARD_NOT_FOUND)
                );
    }
}
