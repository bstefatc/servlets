package com.matrix.simple;

import javax.servlet.AsyncContext;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.ThreadPoolExecutor;

@WebServlet(urlPatterns = {"/multi"}, asyncSupported = true)
public class Multi extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final AsyncContext acontext = request.startAsync();
        ThreadPoolExecutor executor = (ThreadPoolExecutor) request
                .getServletContext().getAttribute("executor");
        executor.execute(new Runnable() {
            public void run() {
                ServletResponse response = acontext.getResponse();
                try {
                    Thread.sleep(30000);
                    response.getWriter().println("x");
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                acontext.complete();
            }
        });
    }
}