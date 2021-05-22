package com.github.chat.handlers;

import com.github.chat.controllers.UsersController;
import com.github.chat.dto.UserAuthDto;
import com.github.chat.dto.UserRegDto;
import com.github.chat.entity.User;
import com.github.chat.exceptions.BadRequest;
import com.github.chat.payload.Token;
import com.github.chat.utils.EmailSender;
import com.github.chat.utils.JsonHelper;
import com.github.chat.utils.TokenProvider;

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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();
        String url = req.getRequestURI();
        String[] urlSplit = url.split("/", 4);
        if(urlSplit[2].equals("verification")) {
            Token t = TokenProvider.decode(urlSplit[3]);
            if(TokenProvider.checkToken(t)) {
                User user = this.usersController.confirmation(new UserRegDto(t.getNickname()));
                if(Objects.nonNull(user)){
                    resp.setStatus(HttpServletResponse.SC_ACCEPTED);
                } else {
                    resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                }
            } else {
                resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                out.write("Expired token!");
            }
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
                boolean result = false;
                if(payload != null) {
                    this.usersController.reg(payload);
                }
                if (result){
                    resp.setStatus(HttpServletResponse.SC_ACCEPTED);
                    EmailSender.sendEmail(payload.getEmail(), TokenProvider.encode(new Token(payload.getNickName())));
                } else {
                    resp.setStatus(403);
                }
            }
        }
    }
}
