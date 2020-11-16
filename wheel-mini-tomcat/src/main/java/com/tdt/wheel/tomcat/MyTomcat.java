package com.tdt.wheel.tomcat;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * description: 你能够看到Tomcat的处理流程：把URL对应处理的Servlet关系形成，解析HTTP协议，封装请求/响应对象，利用反射实例化具体的Servlet进行处理即可
 *
 * @date: 2020年11月16日 17:54
 * @author: qinrenchuan
 */
public class MyTomcat {
    private int port = 8080;
    private Map<String, String> urlServletMap = new HashMap<>();

    public MyTomcat(int port) {
        this.port = port;
    }

    public static void main(String[] args) {
        new MyTomcat(8080).start();
    }

    public void start() {
        // 初始化URL与servlet的关系
        initServletMapping();

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Mytomcat is started ...");

            while (true) {
                Socket socket = serverSocket.accept();
                InputStream inputStream = socket.getInputStream();
                OutputStream outputStream = socket.getOutputStream();

                MyRequest request = new MyRequest(inputStream);
                MyResponse response = new MyResponse(outputStream);

                // 请求分发
                dispatch(request, response);
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private void dispatch(MyRequest request, MyResponse response) {
        String clazz = urlServletMap.get(request.getUrl());

        try {
            if (clazz != null && !"".equals(clazz.trim())) {
                Class<MyServlet> myServletClass = (Class<MyServlet>) Class.forName(clazz);
                MyServlet myServlet = myServletClass.newInstance();
                myServlet.service(request, response);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

    }

    private void initServletMapping() {
        for (ServletMapping servletMapping : ServletMappingConfig.getServletMappings()) {
            urlServletMap.put(servletMapping.getUrl(), servletMapping.getClazz());
        }
    }
}
