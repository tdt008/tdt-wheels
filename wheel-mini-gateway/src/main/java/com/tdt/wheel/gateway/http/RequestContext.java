package com.tdt.wheel.gateway.http;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.RequestEntity;

/**
 * description: RequestContext
 *
 * @date: 2020年11月18日 17:15
 * @author: qinrenchuan
 */
public class RequestContext {
    private HttpServletRequest request;
    private HttpServletResponse response;
    private RequestEntity<byte[]> requestEntity;

    private static RequestContext requestContext = new RequestContext();

    public static RequestContext getCurrentContext() {
        return requestContext;
    }

    private RequestContext() {
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    public RequestEntity<byte[]> getRequestEntity() {
        return requestEntity;
    }

    public void setRequestEntity(RequestEntity<byte[]> requestEntity) {
        this.requestEntity = requestEntity;
    }
}
