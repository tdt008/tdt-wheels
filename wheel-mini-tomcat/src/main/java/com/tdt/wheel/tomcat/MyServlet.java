package com.tdt.wheel.tomcat;

/**
 * description: MyServlet
 *
 * @date: 2020年11月16日 17:09
 * @author: qinrenchuan
 */
public abstract class MyServlet {
    public abstract void doGet(MyRequest request, MyResponse response);
    public abstract void doPost(MyRequest request, MyResponse response);

    public void service(MyRequest request, MyResponse response) {
        if (request.getMethod().equalsIgnoreCase("POST")) {
            doPost(request, response);
        } else if (request.getMethod().equalsIgnoreCase("GET")) {
            doGet(request, response);
        }
    }
}
