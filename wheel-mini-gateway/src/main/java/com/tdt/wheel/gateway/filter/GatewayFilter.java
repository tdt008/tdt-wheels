package com.tdt.wheel.gateway.filter;

/**
 * description: GatewayFilter
 *
 * @date: 2020年11月18日 17:19
 * @author: qinrenchuan
 */
public abstract class GatewayFilter {
    abstract public String filterType();
    abstract public int filterOrder();
    abstract public void run();
}
