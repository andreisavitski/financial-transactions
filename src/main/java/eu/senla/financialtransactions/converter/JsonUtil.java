package eu.senla.financialtransactions.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class JsonUtil {

    ObjectMapper objectMapper = new ObjectMapper();

    public <T> List<T> fromJsonToList(String json, Class<T> clazz) throws IOException {
        return objectMapper.readValue(json, objectMapper.getTypeFactory()
                .constructCollectionType(List.class, clazz));
    }

    public <T> T fromJsonToObj(String json, Class<T> clazz) throws IOException {
        return objectMapper.readValue(json, clazz);
    }
}
