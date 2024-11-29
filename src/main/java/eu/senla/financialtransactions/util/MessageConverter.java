package eu.senla.financialtransactions.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.constraints.NotNull;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.util.List;

@Setter
@UtilityClass
public class MessageConverter {

    @Setter
    private static ObjectMapper objectMapper;

    @SneakyThrows
    @NotNull
    public static <T> T convertToObj(byte[] bytes, @NotNull Class<T> clazz) {
        return objectMapper.readValue(bytes, clazz);
    }

    @NotNull
    public static <T> List<T> convertToListObjects(@NotNull Object o, @NotNull Class<T> clazz) {
        return ((List<?>) o).stream()
                .map(map -> objectMapper.convertValue(map, clazz))
                .toList();
    }
}
