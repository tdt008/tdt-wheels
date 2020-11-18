package com.tdt.wheel.gateway.filter.pre;

import com.tdt.wheel.gateway.filter.GatewayFilter;
import com.tdt.wheel.gateway.http.RequestContext;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.boot.autoconfigure.web.ServerProperties.Jetty.Accesslog.FORMAT;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StreamUtils;

/**
 * description: RequestWrapperFilter
 *
 * @date: 2020年11月18日 17:23
 * @author: qinrenchuan
 */
public class RequestWrapperFilter extends GatewayFilter {
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return -1;
    }

    @Override
    public void run() {
        String rootURL = "http://localhost:9090";
        RequestContext requestConext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestConext.getRequest();
        String targetURL = rootURL + request.getRequestURI();
        RequestEntity<byte[]> requestEntity = null;
        try {
            requestEntity = createRequestEntity(request, targetURL);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 将requestEntity放入全局threadlocal之中
        requestConext.setRequestEntity(requestEntity);
    }

    private RequestEntity<byte[]> createRequestEntity(HttpServletRequest request, String targetURL)
            throws IOException, URISyntaxException {
        String method = request.getMethod();
        HttpMethod httpMethod = HttpMethod.resolve(method);

        // 1、封装请求头
        MultiValueMap<String, String> headers = createRequestHeaders(request);
        // 2、封装请求体
        byte[] body = crateRequestBody(request);
        // 3、构造出RestTemplate能识别的RequestEntity
        RequestEntity requestEntity = new RequestEntity<byte[]>(body,headers,httpMethod, new URI(targetURL));
        return requestEntity;
    }

    private byte[] crateRequestBody(HttpServletRequest request) throws IOException {
        InputStream inputStream = request.getInputStream();
        return StreamUtils.copyToByteArray(inputStream);
    }

    private MultiValueMap<String, String> createRequestHeaders(HttpServletRequest request) {
        HttpHeaders headers = new HttpHeaders();
        List<String> headerNames = Collections.list(request.getHeaderNames());
        for (String headerName : headerNames) {
            List<String> headerValues = Collections.list(request.getHeaders(headerName));
            for (String headerValue : headerValues) {
                headers.add(headerName, headerValue);
            }
        }

        return headers;
    }
}
