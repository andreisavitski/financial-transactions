package eu.senla.financialtransactions.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import eu.senla.financialtransactions.util.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

    @Bean
    public ObjectMapper objectMapper() {
        final ObjectMapper objectMapper = new ObjectMapper();
        MessageConverter.setObjectMapper(objectMapper);
        return objectMapper;
    }
}
