package com.tdt.wheel.gateway.http;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * description: RequestContext
 *
 * @date: 2020年11月18日 17:15
 * @author: qinrenchuan
 */
public class RequestContext {
    private HttpServletRequest req;
    private HttpServletResponse resp;

    private static RequestContext requestContext = new RequestContext();

    public static RequestContext getCurrentContext() {
        return requestContext;
    }

    private RequestContext() {
    }

    public RequestContext setReq(HttpServletRequest req) {
        this.req = req;
        return this;
    }

    public RequestContext setResp(HttpServletResponse resp) {
        this.resp = resp;
        return this;
    }
}
