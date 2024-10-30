package eu.senla.financialtransactions.constant;

public class AppConstants {

    public static final String RABBITMQ_QUEUE_REQUEST_FOR_GET_CARD = "${rabbitmq.queue1.name}";

    public static final String RABBITMQ_QUEUE_RESPONSE_FOR_GET_CARD = "${rabbitmq.queue2.name}";

    public static final String RABBITMQ_QUEUE_REQUEST_FOR_TRANSFER = "${rabbitmq.queue3.name}";

    public static final String RABBITMQ_QUEUE_RESPONSE_FOR_TRANSFER = "${rabbitmq.queue4.name}";

    public static final String RABBITMQ_EXCHANGE = "${rabbitmq.exchange.name}";

    public static final String RABBITMQ_ROUTING_KEY_FOR_REQUEST_GET_CARD = "${rabbitmq.routing1.key}";

    public static final String RABBITMQ_ROUTING_KEY_FOR_REQUEST_TRANSFER = "${rabbitmq.routing3.key}";

    public static final String JWT_KEY = "${jwt.secret-key}";

    public static final String BEARER_PREFIX = "Bearer ";

    public static final String HEADER_NAME = "Authorization";

    public static final String AUTHORITIES = "authorities";

    public static final String PERMISSION_FOR_TRANSFER = "${permissions.permission-for-transfer}";

    public static final String PERMISSION_FOR_GET_CLIENT_CARD = "${permissions.permission-for-get-client-card}";
}
