package com.tdt.wheel.tomcat.test;

import com.tdt.wheel.tomcat.MyRequest;
import com.tdt.wheel.tomcat.MyResponse;
import com.tdt.wheel.tomcat.MyServlet;

import java.io.IOException;

/**
 * description: FindGirlServlet
 *
 * @date: 2020年11月16日 17:13
 * @author: qinrenchuan
 */
public class FindGirlServlet extends MyServlet {
    @Override
    public void doGet(MyRequest request, MyResponse response) {
        try {
            response.write("doGet girl.....");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(MyRequest request, MyResponse response) {
        try {
            response.write("doPost girl.....");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
