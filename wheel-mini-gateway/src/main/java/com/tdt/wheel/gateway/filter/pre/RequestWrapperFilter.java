package com.tdt.wheel.gateway.filter.pre;

import com.tdt.wheel.gateway.filter.GatewayFilter;

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

    }
}
