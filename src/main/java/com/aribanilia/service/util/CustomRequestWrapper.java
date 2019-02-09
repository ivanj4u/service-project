package com.aribanilia.service.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;

public class CustomRequestWrapper extends HttpServletRequestWrapper {
    private final String requestBody;
    private static final Logger logger = LoggerFactory.getLogger(CustomRequestWrapper.class);

    public CustomRequestWrapper(HttpServletRequest request) throws Exception {
        super(request);

        StringBuilder sb = new StringBuilder();
        BufferedReader reader = null;
        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                reader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = reader.read(charBuffer)) > 0) {
                    sb.append(charBuffer, 0, bytesRead);
                }
            } else {
                sb.append("");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error reading the request", e);
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        requestBody = sb.toString();
    }

    public String getData() {
        return this.requestBody;
    }

    @Override
    public ServletInputStream getInputStream ()
            throws IOException {

        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(requestBody.getBytes());
        ServletInputStream inputStream = new ServletInputStream() {
            public int read ()
                    throws IOException {
                return byteArrayInputStream.read();
            }

            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener listener) {
            }
        };
        return inputStream;
    }
}
