package eu.senla.financialtransactions.constant;

import lombok.experimental.UtilityClass;

@UtilityClass
public class RabbitMqConstants {

    public static final String RABBITMQ_QUEUE_REQUEST_FOR_GET_CARD =
            "${rabbitmq.queue_request_for_get_card.name}";

    public static final String RABBITMQ_QUEUE_RESPONSE_FOR_GET_CARD =
            "${rabbitmq.queue_response_for_get_card.name}";

    public static final String RABBITMQ_QUEUE_REQUEST_FOR_ADD_CARD =
            "${rabbitmq.queue_request_for_add_card.name}";

    public static final String RABBITMQ_QUEUE_RESPONSE_FOR_ADD_CARD =
            "${rabbitmq.queue_response_for_add_card.name}";

    public static final String RABBITMQ_QUEUE_REQUEST_FOR_TRANSFER =
            "${rabbitmq.queue_request_for_transfer.name}";

    public static final String RABBITMQ_QUEUE_RESPONSE_FOR_TRANSFER =
            "${rabbitmq.queue_response_for_transfer.name}";

    public static final String RABBITMQ_QUEUE_REQUEST_FOR_PAYMENT =
            "${rabbitmq.queue_request_for_payment.name}";

    public static final String RABBITMQ_QUEUE_RESPONSE_FOR_PAYMENT =
            "${rabbitmq.queue_response_for_payment.name}";

    public static final String RABBITMQ_QUEUE_REQUEST_FOR_OPEN_DEPOSIT =
            "${rabbitmq.queue_request_for_open_deposit.name}";

    public static final String RABBITMQ_QUEUE_RESPONSE_FOR_OPEN_DEPOSIT =
            "${rabbitmq.queue_response_for_open_deposit.name}";

    public static final String RABBITMQ_QUEUE_REQUEST_FOR_UPDATE_DEPOSIT =
            "${rabbitmq.queue_request_for_update_deposit.name}";

    public static final String RABBITMQ_QUEUE_RESPONSE_FOR_UPDATE_DEPOSIT =
            "${rabbitmq.queue_response_for_update_deposit.name}";

    public static final String RABBITMQ_ROUTING_KEY_FOR_REQUEST_GET_CARD =
            "${rabbitmq.routing_for_request_get_card.key}";

    public static final String RABBITMQ_ROUTING_KEY_FOR_REQUEST_ADD_CARD =
            "${rabbitmq.routing_for_request_add_card.key}";

    public static final String RABBITMQ_ROUTING_KEY_FOR_REQUEST_TRANSFER =
            "${rabbitmq.routing_for_request_transfer.key}";

    public static final String RABBITMQ_ROUTING_KEY_FOR_REQUEST_PAYMENT =
            "${rabbitmq.routing_for_request_payment.key}";

    public static final String RABBITMQ_ROUTING_KEY_FOR_REQUEST_OPEN_DEPOSIT =
            "${rabbitmq.routing_for_request_open_deposit.key}";

    public static final String RABBITMQ_ROUTING_KEY_FOR_REQUEST_UPDATE_DEPOSIT =
            "${rabbitmq.routing_for_request_update_deposit.key}";

    public static final String RABBITMQ_EXCHANGE_CARD = "${rabbitmq.exchange_card.name}";
}
