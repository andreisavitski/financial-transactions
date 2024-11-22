package eu.senla.financialtransactions.exception;

import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

@Component
public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {

    @NotNull
    @Override
    public boolean hasError(@NotNull ClientHttpResponse httpResponse) throws IOException {
        return httpResponse.getStatusCode()
                .is5xxServerError() || httpResponse.getStatusCode()
                .is4xxClientError();
    }

    @Override
    public void handleError(@NotNull ClientHttpResponse httpResponse) throws IOException {
        if (httpResponse.getStatusCode()
                .is5xxServerError()) {
            throw new HttpClientErrorException(httpResponse.getStatusCode());
        } else if (httpResponse.getStatusCode()
                .is4xxClientError() && httpResponse.getStatusCode() == HttpStatus.NOT_FOUND) {
            throw new IllegalArgumentException();
        }

    }
}
