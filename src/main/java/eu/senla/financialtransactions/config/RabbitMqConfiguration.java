package eu.senla.financialtransactions.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.RetryInterceptorBuilder;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.retry.RejectAndDontRequeueRecoverer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static eu.senla.financialtransactions.constant.AppConstants.MAXIMUM_ATTEMPTS_FOR_SIMPLE_RETRY_POLICY;
import static eu.senla.financialtransactions.constant.RabbitMqConstants.RABBITMQ_EXCHANGE_CARD;
import static eu.senla.financialtransactions.constant.RabbitMqConstants.RABBITMQ_QUEUE_REQUEST_FOR_ADD_CARD;
import static eu.senla.financialtransactions.constant.RabbitMqConstants.RABBITMQ_QUEUE_REQUEST_FOR_GET_CARD;
import static eu.senla.financialtransactions.constant.RabbitMqConstants.RABBITMQ_QUEUE_REQUEST_FOR_OPEN_DEPOSIT;
import static eu.senla.financialtransactions.constant.RabbitMqConstants.RABBITMQ_QUEUE_REQUEST_FOR_PAYMENT;
import static eu.senla.financialtransactions.constant.RabbitMqConstants.RABBITMQ_QUEUE_REQUEST_FOR_TRANSFER;
import static eu.senla.financialtransactions.constant.RabbitMqConstants.RABBITMQ_QUEUE_REQUEST_FOR_UPDATE_DEPOSIT;
import static eu.senla.financialtransactions.constant.RabbitMqConstants.RABBITMQ_ROUTING_KEY_FOR_REQUEST_ADD_CARD;
import static eu.senla.financialtransactions.constant.RabbitMqConstants.RABBITMQ_ROUTING_KEY_FOR_REQUEST_GET_CARD;
import static eu.senla.financialtransactions.constant.RabbitMqConstants.RABBITMQ_ROUTING_KEY_FOR_REQUEST_OPEN_DEPOSIT;
import static eu.senla.financialtransactions.constant.RabbitMqConstants.RABBITMQ_ROUTING_KEY_FOR_REQUEST_PAYMENT;
import static eu.senla.financialtransactions.constant.RabbitMqConstants.RABBITMQ_ROUTING_KEY_FOR_REQUEST_TRANSFER;
import static eu.senla.financialtransactions.constant.RabbitMqConstants.RABBITMQ_ROUTING_KEY_FOR_REQUEST_UPDATE_DEPOSIT;

@Configuration
public class RabbitMqConfiguration {

    @Value(RABBITMQ_QUEUE_REQUEST_FOR_GET_CARD)
    private String queueRequestForGetCard;

    @Value(RABBITMQ_QUEUE_REQUEST_FOR_ADD_CARD)
    private String queueRequestForAddCard;

    @Value(RABBITMQ_QUEUE_REQUEST_FOR_TRANSFER)
    private String queueRequestForTransfer;

    @Value(RABBITMQ_QUEUE_REQUEST_FOR_PAYMENT)
    private String queueRequestForPayment;

    @Value(RABBITMQ_QUEUE_REQUEST_FOR_OPEN_DEPOSIT)
    private String queueRequestForOpenDeposit;

    @Value(RABBITMQ_QUEUE_REQUEST_FOR_UPDATE_DEPOSIT)
    private String queueRequestForUpdateDeposit;

    @Value(RABBITMQ_EXCHANGE_CARD)
    private String exchangeCard;

    @Value(RABBITMQ_ROUTING_KEY_FOR_REQUEST_GET_CARD)
    private String routingKeyForRequestGetCard;

    @Value(RABBITMQ_ROUTING_KEY_FOR_REQUEST_ADD_CARD)
    private String routingKeyForRequestAddCard;

    @Value(RABBITMQ_ROUTING_KEY_FOR_REQUEST_TRANSFER)
    private String routingKeyForRequestTransfer;

    @Value(RABBITMQ_ROUTING_KEY_FOR_REQUEST_PAYMENT)
    private String routingKeyForRequestPayment;

    @Value(RABBITMQ_ROUTING_KEY_FOR_REQUEST_OPEN_DEPOSIT)
    private String routingKeyForRequestOpenDeposit;

    @Value(RABBITMQ_ROUTING_KEY_FOR_REQUEST_UPDATE_DEPOSIT)
    private String routingKeyForRequestUpdateDeposit;

    @Bean
    public Queue queueRequestForGetCard() {
        return new Queue(queueRequestForGetCard);
    }

    @Bean
    public Queue queueRequestForAddCard() {
        return new Queue(queueRequestForAddCard);
    }

    @Bean
    public Queue queueRequestForTransfer() {
        return new Queue(queueRequestForTransfer);
    }

    @Bean
    public Queue queueRequestForPayment() {
        return new Queue(queueRequestForPayment);
    }

    @Bean
    public Queue queueRequestForOpenDeposit() {
        return new Queue(queueRequestForOpenDeposit);
    }

    @Bean
    public Queue queueRequestForUpdateDeposit() {
        return new Queue(queueRequestForUpdateDeposit);
    }

    @Bean
    public DirectExchange exchangeCard() {
        return new DirectExchange(exchangeCard);
    }

    @Bean
    public Binding bindingForRequestGetCard() {
        return BindingBuilder
                .bind(queueRequestForGetCard())
                .to(exchangeCard())
                .with(routingKeyForRequestGetCard);
    }

    @Bean
    public Binding bindingForRequestAddCard() {
        return BindingBuilder
                .bind(queueRequestForAddCard())
                .to(exchangeCard())
                .with(routingKeyForRequestAddCard);
    }

    @Bean
    public Binding bindingForRequestTransfer() {
        return BindingBuilder
                .bind(queueRequestForTransfer())
                .to(exchangeCard())
                .with(routingKeyForRequestTransfer);
    }

    @Bean
    public Binding bindingForRequestPayment() {
        return BindingBuilder
                .bind(queueRequestForPayment())
                .to(exchangeCard())
                .with(routingKeyForRequestPayment);
    }

    @Bean
    public Binding bindingForOpenDeposit() {
        return BindingBuilder
                .bind(queueRequestForOpenDeposit())
                .to(exchangeCard())
                .with(routingKeyForRequestOpenDeposit);
    }

    @Bean
    public Binding bindingForUpdateDeposit() {
        return BindingBuilder
                .bind(queueRequestForUpdateDeposit())
                .to(exchangeCard())
                .with(routingKeyForRequestUpdateDeposit);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(
            ConnectionFactory connectionFactory) {
        final SimpleRabbitListenerContainerFactory factory =
                new SimpleRabbitListenerContainerFactory();
        factory.setAdviceChain(
                RetryInterceptorBuilder
                        .stateless()
                        .maxAttempts(MAXIMUM_ATTEMPTS_FOR_SIMPLE_RETRY_POLICY)
                        .recoverer(new RejectAndDontRequeueRecoverer())
                        .build()
        );
        factory.setConnectionFactory(connectionFactory);
        return factory;
    }
}
