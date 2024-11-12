package eu.senla.financialtransactions.enums;

import eu.senla.financialtransactions.constant.AppConstants;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;

@Getter
@RequiredArgsConstructor
public enum ActivitySort {

    NAME_ASC(Sort.by(Sort.Direction.ASC, AppConstants.ACTIVITY_FIELD_NAME)),

    NAME_DESC(Sort.by(Sort.Direction.DESC, AppConstants.ACTIVITY_FIELD_NAME));

    private final Sort sortValue;
}
