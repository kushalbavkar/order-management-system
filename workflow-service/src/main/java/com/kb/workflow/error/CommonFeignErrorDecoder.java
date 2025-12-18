package com.kb.workflow.error;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kb.common.error.ApiErrorResponse;
import com.kb.workflow.exceptions.DownstreamException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Component
public class CommonFeignErrorDecoder implements ErrorDecoder {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Exception decode(String s, Response response) {
        try (InputStream is = response.body().asInputStream()) {
            final ApiErrorResponse error = objectMapper.readValue(is, ApiErrorResponse.class);
            return new DownstreamException(error.message());
        } catch (Exception e) {
            return new DownstreamException("Downstream service error");
        }
    }
}
