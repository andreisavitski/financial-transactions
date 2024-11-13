package eu.senla.financialtransactions.constant;

public class AppConstants {

    public static final String RABBITMQ_QUEUE_REQUEST_FOR_GET_CARD =
            "${rabbitmq.queue_request_for_get_card.name}";

    public static final String RABBITMQ_QUEUE_RESPONSE_FOR_GET_CARD =
            "${rabbitmq.queue_response_for_get_card.name}";

    public static final String RABBITMQ_QUEUE_REQUEST_FOR_TRANSFER =
            "${rabbitmq.queue_request_for_transfer.name}";

    public static final String RABBITMQ_QUEUE_RESPONSE_FOR_TRANSFER =
            "${rabbitmq.queue_response_for_transfer.name}";

    public static final String RABBITMQ_QUEUE_REQUEST_FOR_PAYMENT =
            "${rabbitmq.queue_request_for_payment.name}";

    public static final String RABBITMQ_QUEUE_RESPONSE_FOR_PAYMENT =
            "${rabbitmq.queue_response_for_payment.name}";

    public static final String RABBITMQ_ROUTING_KEY_FOR_REQUEST_GET_CARD =
            "${rabbitmq.routing_for_request_get_card.key}";

    public static final String RABBITMQ_ROUTING_KEY_FOR_REQUEST_TRANSFER =
            "${rabbitmq.routing_for_request_transfer.key}";

    public static final String RABBITMQ_ROUTING_KEY_FOR_REQUEST_PAYMENT =
            "${rabbitmq.routing_for_request_payment.key}";

    public static final String RABBITMQ_EXCHANGE_CARD = "${rabbitmq.exchange_card.name}";

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

    public static final String SPRING = "spring";
}
