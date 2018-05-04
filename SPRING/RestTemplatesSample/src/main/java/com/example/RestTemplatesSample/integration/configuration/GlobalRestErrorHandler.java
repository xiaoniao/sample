package com.example.RestTemplatesSample.integration.configuration;

import com.example.RestTemplatesSample.integration.exception.RestException;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

/**
 * Created by liuzz on 2018/05/04
 */
public class GlobalRestErrorHandler implements ResponseErrorHandler {

    private Logger log = LoggerFactory.getLogger(GlobalRestErrorHandler.class);

    @Override
    public boolean hasError(ClientHttpResponse clientHttpResponse) throws IOException {
        log.error("{}", clientHttpResponse.getStatusCode());

        // HTTP ERROR
        if (clientHttpResponse.getRawStatusCode() != HttpStatus.OK.value()) {
            return true;
        }

        // BIZ ERROR ?? 自定义json解析器
        return false;
    }

    @Override
    public void handleError(ClientHttpResponse clientHttpResponse) throws IOException {
        throw new RestException(clientHttpResponse.getStatusCode().toString());
    }
}
