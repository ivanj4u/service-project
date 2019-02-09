package com.aribanilia.service.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

@Configuration
public class AuthResponseHandler implements ResponseErrorHandler {

    private static final Logger logger = LoggerFactory.getLogger(AuthResponseHandler.class);

    @Override
    public boolean hasError(ClientHttpResponse clientHttpResponse) throws IOException {
        /**
         * UNAUTHORIZE = true
         */
        if (clientHttpResponse.getStatusCode() == HttpStatus.NOT_FOUND)
            return false;
        else
            return true;
    }

    @Override
    public void handleError(ClientHttpResponse clientHttpResponse) throws IOException {
        logger.info(clientHttpResponse.getStatusCode() + " - " + clientHttpResponse.getStatusCode().getReasonPhrase());
    }
}
