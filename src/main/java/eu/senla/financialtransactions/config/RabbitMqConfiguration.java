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

import static eu.senla.financialtransactions.constant.AppConstants.*;

@Configuration
public class RabbitMqConfiguration {

    @Value(RABBITMQ_QUEUE_REQUEST_FOR_GET_CARD)
    private String queueRequestForGetCard;

    @Value(RABBITMQ_QUEUE_REQUEST_FOR_TRANSFER)
    private String queueRequestForTransfer;

    @Value(RABBITMQ_QUEUE_REQUEST_FOR_PAYMENT)
    private String queueRequestForPayment;

    @Value(RABBITMQ_EXCHANGE_CARD)
    private String exchangeCard;

    @Value(RABBITMQ_ROUTING_KEY_FOR_REQUEST_GET_CARD)
    private String routingKeyForRequestGetCard;

    @Value(RABBITMQ_ROUTING_KEY_FOR_REQUEST_TRANSFER)
    private String routingKeyForRequestTransfer;

    @Value(RABBITMQ_ROUTING_KEY_FOR_REQUEST_PAYMENT)
    private String routingKeyForRequestPayment;

    @Bean
    public Queue queueRequestForGetCard() {
        return new Queue(queueRequestForGetCard);
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
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(
            ConnectionFactory connectionFactory) {
        final SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setAdviceChain(
                RetryInterceptorBuilder
                        .stateless()
                        .maxAttempts(5)
                        .recoverer(new RejectAndDontRequeueRecoverer())
                        .build()
        );
        factory.setConnectionFactory(connectionFactory);
        return factory;
    }
}
