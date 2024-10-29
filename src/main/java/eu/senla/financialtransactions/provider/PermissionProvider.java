package eu.senla.financialtransactions.provider;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static eu.senla.financialtransactions.constant.AppConstants.PERMISSION_FOR_TRANSFER;
import static eu.senla.financialtransactions.constant.AppConstants.PERMISSION_FOR_GET_CLIENT_CARD;
import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Component
@Scope(value = SCOPE_PROTOTYPE)
@Getter
public class PermissionProvider {

    @Value(value = PERMISSION_FOR_TRANSFER)
    private String permissionForTransfer;

    @Value(value = PERMISSION_FOR_GET_CLIENT_CARD)
    private String permissionForGetClientCard;
}
