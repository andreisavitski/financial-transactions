package eu.senla.financialtransactions.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MessageUtil {

    private final ObjectMapper objectMapper;

    @SneakyThrows
    public <T> List<T> convertToList(byte[] bytes, Class<T> clazz) {
        return objectMapper.readValue(bytes, objectMapper.getTypeFactory()
                .constructCollectionType(List.class, clazz));
    }

    @SneakyThrows
    public <T> T convertToObj(byte[] bytes, Class<T> clazz) {
        return objectMapper.readValue(bytes, clazz);
    }
}
