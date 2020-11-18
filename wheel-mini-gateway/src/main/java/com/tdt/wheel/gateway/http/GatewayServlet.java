package com.tdt.wheel.gateway.http;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * description: GatewayServlet
 *
 * @date: 2020年11月18日 17:09
 * @author: qinrenchuan
 */
@WebServlet(name = "gatewayServlet", urlPatterns = "/*")
public class GatewayServlet extends HttpServlet {
    private GatewayRunner gatewayRunner = new GatewayRunner();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 将req resp放入上下文中
        gatewayRunner.init(req, resp);

        try {
            // 执行前置过滤
            gatewayRunner.preRoute();
            // 执行过滤
            gatewayRunner.route();
            // 执行后置过滤
            gatewayRunner.postRoute();
        } catch (Throwable e) {
            RequestContext.getCurrentContext().getResponse()
                    .sendError(HttpServletResponse.SC_NOT_FOUND, e.getMessage());
        } finally {
            // 清除变量
            RequestContext.getCurrentConext().unset();
        }
    }
}
