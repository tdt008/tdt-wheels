package com.tdt.wheel.gateway.http;

import com.tdt.wheel.gateway.filter.GatewayFilter;
import com.tdt.wheel.gateway.filter.post.SendResponseFilter;
import com.tdt.wheel.gateway.filter.pre.RequestWrapperFilter;
import com.tdt.wheel.gateway.filter.route.RoutingFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * description: GatewayRunner
 *
 * @date: 2020年11月18日 17:12
 * @author: qinrenchuan
 */
public class GatewayRunner {
    private ConcurrentHashMap<String, List<GatewayFilter>> hashFilterByType =
            new ConcurrentHashMap<>();

    public void init(HttpServletRequest req, HttpServletResponse resp) {
        hashFilterByType.put("pre", new ArrayList<GatewayFilter>(){{
            add(new RequestWrapperFilter());
        }});
        hashFilterByType.put("post", new ArrayList<GatewayFilter>(){{
            add(new SendResponseFilter());
        }});
        hashFilterByType.put("route", new ArrayList<GatewayFilter>(){{
            add(new RoutingFilter());
        }});

        RequestContext.getCurrentContext()
                .setReq(req)
                .setResp(resp);
    }

    public void preRoute() {
        runFilters("pre");
    }

    public void route() {
        runFilters("route");
    }

    public void postRoute() {
        runFilters("post");
    }

    public void runFilters(String filterType) {
        List<GatewayFilter> gatewayFilters = this.hashFilterByType.get(filterType);
        if (gatewayFilters != null) {
            for (GatewayFilter gatewayFilter : gatewayFilters) {
                gatewayFilter.run();
            }
        }
    }
}
