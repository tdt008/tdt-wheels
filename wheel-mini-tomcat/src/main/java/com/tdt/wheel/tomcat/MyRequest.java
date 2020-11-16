package com.tdt.wheel.tomcat;

import java.io.IOException;
import java.io.InputStream;

/**
 * description: 我们通过输入流，对HTTP协议进行解析，拿到了HTTP请求头的方法以及URL
 *
 * @date: 2020年11月16日 15:48
 */
public class MyRequest {
    private String url;
    private String method;

    public MyRequest(InputStream inputStream) throws IOException {
        String httpRequest = "";
        byte[] httpRequestBytes = new byte[1024];
        int length = 0;
        if ((length = inputStream.read(httpRequestBytes)) > 0) {
            httpRequest = new String(httpRequestBytes, 0, length);
        }
        // http协议
        // GET /admin/manager-host/manager/list?pageSize=20&pageNum=1&erbanNosStr=&_=1605510759602 HTTP/1.1
        // Host: adminbeta.allolike.com
        // Connection: keep-alive
        // Accept: application/json, text/javascript, */*; q=0.01
        // X-Requested-With: XMLHttpRequest
        // User-Agent: Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36
        // Content-Type: application/json
        // Referer: http://adminbeta.allolike.com/admin/main.action
        // Accept-Encoding: gzip, deflate
        // Accept-Language: zh-CN,zh;q=0.9
        // Cookie: JSESSIONID=E6E61E85496F51FCF3BF72B75828B9C7

        String httpHead = httpRequest.split("\n")[0];
        this.url = httpHead.split("\\s")[1];
        this.method = httpHead.split("\\s")[0];


        System.out.println(this);
    }

    public String getUrl() {
        return url;
    }

    public String getMethod() {
        return method;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("MyRequest{");
        sb.append("url='").append(url).append('\'');
        sb.append(", method='").append(method).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
