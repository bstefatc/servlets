package com.matrix.simple;

import javax.servlet.AsyncContext;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/single"})
public class Single extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Thread.sleep(10000);
            response.setContentType("text/html");
            response.getWriter().println("Hello");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
