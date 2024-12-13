package eu.senla.financialtransactions.constant;

import lombok.experimental.UtilityClass;

@UtilityClass
public class AppConstants {

    public static final String JWT_KEY = "${jwt.secret-key}";

    public static final String BEARER_PREFIX = "Bearer ";

    public static final String HEADER_NAME = "Authorization";

    public static final String AUTHORITIES = "authorities";

    public static final String DELETION_INTERVAL_FOR_UNCOMPLETED_TRANSFERS = "0 */5 * * * *";

    public static final String DELETION_INTERVAL_FOR_UNCOMPLETED_PAYMENT = "0 */5 * * * *";

    public static final String SCHEDULER_ENABLED = "scheduler.enabled";

    public static final String PERMISSIONS = "permissions";

    public static final Long MINIMUM_TRANSFER_THRESHOLD = 0L;

    public static final String ACTIVITY_FIELD_NAME = "name";

    public static final String OFFSET = "offset";

    public static final String LIMIT = "limit";

    public static final String SORT = "sort";

    public static final String DEFAULT_OFFSET = "0";

    public static final String DEFAULT_LIMIT = "10";

    public static final String REQUEST_ID = "requestId";

    public static final String CLIENT_ID = "clientId";

    public static final int MAXIMUM_ATTEMPTS_FOR_SIMPLE_RETRY_POLICY = 5;

    public static final long MIN_OFFSET = 0;

    public static final long MIN_LIMIT = 1;

    public static final long MAX_LIMIT = 20;

    public static final int BEGIN_INDEX_HEADER_SUBSTRING = 7;

    public static final int TIMEOUT_EXCHANGER = 5;

    public static final String OK = "ok";

    public static final String ID = "id";
}
