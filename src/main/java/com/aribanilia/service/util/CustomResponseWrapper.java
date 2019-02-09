package com.aribanilia.service.util;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.*;

public class CustomResponseWrapper extends HttpServletResponseWrapper {
    private ByteArrayOutputStream byteArrayOutputStream;

    public CustomResponseWrapper(HttpServletResponse response) throws Exception {
        super(response);
        byteArrayOutputStream = new ByteArrayOutputStream();
    }

    public byte[] getData() {
        return byteArrayOutputStream.toByteArray();
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return new CustomServletOutputStream(byteArrayOutputStream);
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        return new PrintWriter(getOutputStream(), true);
    }

    private class CustomServletOutputStream extends ServletOutputStream {

        private DataOutputStream dataOutputStream;
        private StringBuffer sb;

        public CustomServletOutputStream(OutputStream outputStream) {
            dataOutputStream = new DataOutputStream(outputStream);
            sb = new StringBuffer();
        }

        @Override
        public boolean isReady() {
            return false;
        }

        @Override
        public void setWriteListener(WriteListener listener) {
        }

        @Override
        public void write(int b) throws IOException {
            dataOutputStream.write(b);
            sb.append((char) b);
        }

        @Override
        public void write(byte[] b) throws IOException {
            dataOutputStream.write(b);
            sb.append(new String(b));
        }

        @Override
        public void write(byte[] b, int off, int len) throws IOException {
            dataOutputStream.write(b, off, len);
            sb.append(new String(b));
        }
    }
}
