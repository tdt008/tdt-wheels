package com.tdt.wheel.tomcat;

import java.io.IOException;
import java.io.OutputStream;

/**
 * description: 基于HTTP协议的格式进行输出写入
 *
 * @date: 2020年11月16日 16:46
 */
public class MyResponse {
    private OutputStream outputStream;

    public MyResponse(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void write(String content) throws IOException {
        // Http响应协议
        // HTTP/1.1 200 OK
        // Content-Type: text/html
        // <html><body></body></html>

        StringBuffer httpResponse = new StringBuffer();
        httpResponse.append("HTTP/1.1 200 OK\n")
                .append("Content-Type: text/html\n")
                .append("\r\n")
                .append("<html><body>")
                .append(content)
                .append("</body></html>");
        outputStream.write(httpResponse.toString().getBytes());
        outputStream.close();
    }
}
