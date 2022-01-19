package com.lorenzoferrante.demo;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/HelloServlet")
public class HelloServlet extends HttpServlet {

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        CustomMessage customMessage = new CustomMessage();
        out.println("<h1>Hello " + customMessage.getMessage() + "</h1>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}