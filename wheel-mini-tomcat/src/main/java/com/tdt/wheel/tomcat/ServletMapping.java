package com.tdt.wheel.tomcat;

/**
 * description: ServletMapping
 *
 * @date: 2020年11月16日 17:47
 * @author: qinrenchuan
 */
public class ServletMapping {
    private String servletName;
    private String url;
    private String clazz;

    public ServletMapping(String servletName, String url, String clazz) {
        this.servletName = servletName;
        this.url = url;
        this.clazz = clazz;
    }

    public String getServletName() {
        return servletName;
    }

    public ServletMapping setServletName(String servletName) {
        this.servletName = servletName;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public ServletMapping setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getClazz() {
        return clazz;
    }

    public ServletMapping setClazz(String clazz) {
        this.clazz = clazz;
        return this;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ServletMapping{");
        sb.append("servletName='").append(servletName).append('\'');
        sb.append(", url='").append(url).append('\'');
        sb.append(", clazz='").append(clazz).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
