package ru.javawebinar.basejava.web;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResumeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String charset = "UTF-8";
        request.setCharacterEncoding(charset);
        response.setCharacterEncoding(charset);
        response.setHeader("Content-Type", "text/html; charset=" + charset);
        response.setContentType("text/html; charset=" + charset);

        String name = request.getParameter("name");
        response.getWriter().write( name ==null ? "Hello resumes" : "Hello " + name + "!");

    }
}
