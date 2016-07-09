package com.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by rkasha on 01-Jul-16.
 */
public class HelloWorld extends HttpServlet {

    String message;
    @Override
    public void init(){
        message ="Hello world";
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException {
        response.setContentType("text/html");
        response.getWriter().print("<h1>"+message+"</h1>");
    }

    public void destroy(){
        //do nothing
    }
}
