package com.aribanilia.service.handler;

import com.aribanilia.service.dto.ResponseService;
import com.aribanilia.service.exception.RestAccountNotFoundException;
import com.aribanilia.service.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.oauth2.common.exceptions.InsufficientScopeException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice()
public class ExceptionResponseHandler extends ResponseEntityExceptionHandler {

    private final static Logger logger = LoggerFactory.getLogger(ExceptionResponseHandler.class);

    @ExceptionHandler({AccessDeniedException.class, InsufficientScopeException.class, OAuth2Exception.class})
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<Object> handleAccessDeniedExpetion(Exception ex) {
        ResponseService response = new ResponseService();
        response.setResponseCode(HttpStatus.UNAUTHORIZED.toString());
        response.setResponseDesc(ex.getLocalizedMessage());
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
    }

    @ExceptionHandler(RestAccountNotFoundException.class)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<Object> handleRestHttpConnectionException(Exception ex) {
        ResponseService response = new ResponseService();
        response.setResponseCode(Constants.RESPONSE.INVALID_ACCOUNT_NOT_FOUND.getCode());
        response.setResponseDesc(ex.getMessage());
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * Validating all Exception
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {
        ex.printStackTrace();
        logger.error(" Exception --> " + ex.getMessage());

        ResponseService response = new ResponseService();
        response.setResponseCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
        response.setResponseDesc("Error Core System");
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
