package com.aribanilia.service.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

public class LoggerFilter extends GenericFilterBean {
    private static final Logger logger = LoggerFactory.getLogger(LoggerFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest wrappedRequest = (HttpServletRequest) request;
        HttpServletResponse wrappedResponse = (HttpServletResponse) response;

        CustomRequestWrapper logRequestWrapper;
        CustomResponseWrapper logResponseWrapper;
        OutputStream outputStream;
        try {
            logRequestWrapper = new CustomRequestWrapper(wrappedRequest);
            logResponseWrapper = new CustomResponseWrapper(wrappedResponse);
            outputStream = response.getOutputStream();

            chain.doFilter(logRequestWrapper, logResponseWrapper);

            logger.info("Request IP : " +  logRequestWrapper.getRemoteHost() + " - " + logRequestWrapper.getRemoteAddr() + " : " + logRequestWrapper.getRemotePort());
            logger.info("Request URL : " + logRequestWrapper.getRequestURI());
            logger.info("Request Body : " + logRequestWrapper.getData());
            logger.info("Response Body : " + new String(logResponseWrapper.getData()));

            outputStream.write(logResponseWrapper.getData());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }

}
