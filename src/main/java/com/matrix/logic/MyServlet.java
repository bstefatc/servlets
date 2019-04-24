package com.matrix.logic;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ThreadLocalRandom;

@WebServlet(name = "myServlet", urlPatterns = {"/async-logic"},
        asyncSupported = true)
public class MyServlet extends HttpServlet {
    @Override
    protected void doGet (HttpServletRequest req,
                          HttpServletResponse resp) throws ServletException, IOException {

        final PrintWriter writer = resp.getWriter();
        AsyncContext asyncContext = req.startAsync();
        asyncContext.addListener(new MyAsyncListener());

        final AsyncContext finalAsyncContext = asyncContext;
        asyncContext.start(new Runnable() {
            @Override
            public void run () {
                String msg = task();
                writer.println(msg);
                finalAsyncContext.complete();
            }
        });
    }

    private String task () {
        long start = System.currentTimeMillis();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "time to complete long task " + (System.currentTimeMillis() - start);
    }
}
