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

    public static final String RABBITMQ_ROUTING_KEY_FOR_REQUEST_GET_CARD =
            "${rabbitmq.routing_for_request_get_card.key}";

    public static final String RABBITMQ_ROUTING_KEY_FOR_REQUEST_TRANSFER =
            "${rabbitmq.routing_for_request_transfer.key}";

    public static final String RABBITMQ_EXCHANGE_CARD_TRANSFER = "${rabbitmq.exchange_card_transfer.name}";

    public static final String JWT_KEY = "${jwt.secret-key}";

    public static final String BEARER_PREFIX = "Bearer ";

    public static final String HEADER_NAME = "Authorization";

    public static final String AUTHORITIES = "authorities";

    public static final String PERMISSION_FOR_TRANSFER = "${permissions.permission_for_transfer}";

    public static final String PERMISSION_FOR_GET_CLIENT_CARD =
            "${permissions.permission_for_get_client_card}";
}
