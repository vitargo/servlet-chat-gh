package com.github.chat.handlers;

import com.github.chat.controllers.UsersController;
import com.github.chat.dto.UserAuthDto;
import com.github.chat.dto.UserRegDto;
import com.github.chat.exceptions.BadRequest;
import com.github.chat.utils.JsonHelper;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;
import java.util.stream.Collectors;


public class UsersHandler extends HttpServlet {

    private final UsersController usersController;

    public UsersHandler(UsersController usersController) {
        this.usersController = usersController;
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            super.service(req, resp);
        } catch (BadRequest e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid body");
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        String url = req.getRequestURI();
        if (url.equals("/chat/reg")) {
            RequestDispatcher view = req.getRequestDispatcher("/registration.html");
            view.forward(req, resp);
        } else {
            RequestDispatcher view = req.getRequestDispatcher("/index.html");
            view.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        PrintWriter out = resp.getWriter();
        String body = req.getReader().lines().collect(Collectors.joining());
        if (!"application/json".equalsIgnoreCase(req.getHeader("Content-Type"))) {
            resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE, "Invalid content type");
        } else {
            String url = req.getRequestURI();
            if (url.equals("/chat/auth")) {
                UserAuthDto payload = JsonHelper.fromJson(body, UserAuthDto.class).orElseThrow(BadRequest::new);
                String result = this.usersController.auth(payload);
                if (!Objects.isNull(result)){
                    resp.setContentType("text/html");
                    resp.setStatus(200);
                    out.write(result);
                } else {
                    resp.setStatus(403);
                    RequestDispatcher view = req.getRequestDispatcher("/index.html");
                    view.include(req, resp);
                }
            }
            if (url.equals("/chat/reg")) {
                UserRegDto payload = JsonHelper.fromJson(body, UserRegDto.class).orElseThrow(BadRequest::new);
                boolean result = this.usersController.reg(payload);
                if (result){
                    resp.setStatus(200);
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.html");
                    out = resp.getWriter();
                    out.println("<font color=greed>Registration is successful! Please login. Status " + resp.getStatus() + "! </font>");
                    rd.include(req, resp);
                } else {
                    resp.setStatus(403);
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.html");
                    out = resp.getWriter();
                    out.println("<font color=red>Registration failed. User is already exist! Status " + resp.getStatus() + "! </font>");
                    rd.include(req, resp);
                }
            }
        }
    }
}
