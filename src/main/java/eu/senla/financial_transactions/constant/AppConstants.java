package eu.senla.financial_transactions.constant;

public class AppConstants {

    public static final String RABBITMQ_QUEUE_1 = "${rabbitmq.queue1.json.name}";

    public static final String RABBITMQ_QUEUE_2 = "${rabbitmq.queue2.json.name}";

    public static final String RABBITMQ_EXCHANGE = "${rabbitmq.exchange.name}";

    public static final String RABBITMQ_ROUTING1_KEY = "${rabbitmq.routing1.json.key}";

    public static final String RABBITMQ_ROUTING2_KEY = "${rabbitmq.routing2.json.key}";

    public static final String JWT_KEY = "${jwt.secret-key}";

    public static final String BEARER_PREFIX = "Bearer ";

    public static final String HEADER_NAME = "Authorization";

    public static final String AUTHORITIES = "authorities";
}
