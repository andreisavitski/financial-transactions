package eu.senla.financialtransactions.provider;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import static eu.senla.financialtransactions.constant.AppConstants.PERMISSIONS;

@Data
@Configuration
@ConfigurationProperties(prefix = PERMISSIONS)
public class PermissionProvider {

    private String permissionForTransfer;

    private String permissionForGetClientCard;
}
