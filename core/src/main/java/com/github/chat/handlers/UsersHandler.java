package com.github.chat.handlers;

import com.github.chat.controllers.UsersController;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UsersHandler extends HttpServlet {


    private final UsersController usersController;

    public UsersHandler(UsersController usersController) {
        this.usersController = usersController;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException,
            IOException {
        ServletOutputStream out = resp.getOutputStream();
        out.write("hello Tomcat".getBytes());
        out.flush();
        out.close();
    }
}
