package com.tdt.wheel.tomcat;

import java.util.ArrayList;
import java.util.List;

/**
 * description: 我们在servlet开发中，会在web.xml中通过和来进行指定哪个URL交给哪个servlet进行处理
 *
 * @date: 2020年11月16日 17:50
 * @author: qinrenchuan
 */
public class ServletMappingConfig {
    private static List<ServletMapping> servletMappings = new ArrayList<>();

    static {
        servletMappings.add(new ServletMapping("findGirl", "/girl", "com.tdt.wheel.tomcat.test.FindGirlServlet"));
        servletMappings.add(new ServletMapping("helloWorld", "/helloWorld", "com.tdt.wheel.tomcat.test.HelloWorldServlet"));
    }

    public static List<ServletMapping> getServletMappings() {
        return servletMappings;
    }
}
