package com.github.chat.handlers;

import com.github.chat.controllers.UsersController;
import com.github.chat.dto.RoomRegDto;
import com.github.chat.dto.UserAuthDto;
import com.github.chat.dto.UserRegDto;
import com.github.chat.entity.Room;
import com.github.chat.entity.User;
import com.github.chat.exceptions.BadRequest;
import com.github.chat.payload.Token;
import com.github.chat.utils.EmailSender;
import com.github.chat.utils.JsonHelper;
import com.github.chat.utils.TokenProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import java.util.stream.Collectors;


public class UsersHandler extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(UsersHandler.class);

    private final UsersController usersController;

    public UsersHandler(UsersController usersController) {
        this.usersController = usersController;
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            super.service(req, resp);
            log.info("Start service method");
            log.info(httpServletRequestToString(req));

        } catch (BadRequest e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid body");
        }
    }

    @Override
    public void doOptions(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        log.info("Start doOptions method");
        log.info(httpServletRequestToString(req));
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE, PATCH, OPTIONS");
        resp.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
        resp.setHeader("Content-Type", "application/json");
        resp.setStatus(204);
        System.out.println("Response 204");;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        log.info("Start doGet method");
        PrintWriter out = resp.getWriter();
        String url = req.getRequestURI();
        if(url.equals("/chat/myaccount")) {
            String token = req.getHeader("Authorization");
            Token t = TokenProvider.decode(token);
            if (TokenProvider.checkToken(t)) {
                User user = this.usersController.confirmation(new UserRegDto(t.getNickname()));
                if (Objects.nonNull(user)) {
                    System.out.println(200);
                    resp.setContentType("application/json");
                    resp.setHeader("Access-Control-Allow-Origin", "*");
                    out.write(JsonHelper.toJson(new User(user.getNickName(),
                            user.getFirstName(),
                            user.getLastName(),
                            user.getEmail(),
                            user.getLogin(),
                            null,
                            user.getPhone(),
                            user.getCompanyName(),
                            user.getAvatar())).get());
                } else {
                    resp.setHeader("Access-Control-Allow-Origin", "*");
                    resp.setStatus(403);
                }
            }
            if (url.substring(1).contains("/")) {
                String[] urlSplit = url.split("/", 4);
                if (urlSplit[2].equals("verification")) {
                    Token tok = TokenProvider.decode(urlSplit[3]);
                    if (TokenProvider.checkToken(t)) {
                        User user = this.usersController.confirmation(new UserRegDto(tok.getNickname()));
                        if (Objects.nonNull(user)) {
                            resp.setHeader("Access-Control-Allow-Origin", "*");
                            resp.setStatus(200);
                        } else {
                            resp.setHeader("Access-Control-Allow-Origin", "*");
                            resp.setStatus(403);
                        }
                    } else {
                        resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                        out.write("Expired token!");
                    }
                }
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        log.info("Start doPost method");
        PrintWriter out = resp.getWriter();
        String body = req.getReader().lines().collect(Collectors.joining());
        if (!"application/json".equalsIgnoreCase(req.getHeader("Content-Type"))) {
            resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE, "Invalid content type");
        } else {
            String url = req.getRequestURI();
            if (url.equals("/chat/auth")) {
                UserAuthDto payload = JsonHelper.fromJson(body, UserAuthDto.class).orElseThrow(BadRequest::new);
                String result = null;
                if(payload != null){
                    result = this.usersController.auth(payload);
                    log.info(result);
                }
                if (Objects.nonNull(result)){
                    resp.setContentType("application/json");
                    resp.setHeader("Access-Control-Allow-Origin", "*");
                    resp.setStatus(200);
                    out.write(result);
                    System.out.println("Send 200");
                } else {
                    resp.setHeader("Access-Control-Allow-Origin", "*");
                    resp.setStatus(403);
                    System.out.println("Send 403");
                }
            }
            if (url.equals("/chat/reg")) {
                UserRegDto payload = JsonHelper.fromJson(body, UserRegDto.class).orElseThrow(BadRequest::new);
                String result = null;
                if(payload != null) {
                    result = this.usersController.reg(payload);
                }
                if (result != null){
                    resp.setHeader("Access-Control-Allow-Origin", "*");
                    resp.setStatus(200);
                    EmailSender.sendEmail(payload.getEmail(), result);
                } else {
                    resp.setHeader("Access-Control-Allow-Origin", "*");
                    resp.setStatus(403);
                }
            }
            if (url.equals("/chat/createroom")) {
                String token = req.getHeader("Authorization");
                Token t = TokenProvider.decode(token);
                if (TokenProvider.checkToken(t)) {
                    RoomRegDto payload = JsonHelper.fromJson(body, RoomRegDto.class).orElseThrow(BadRequest::new);
                    long adminId = t.getId();
                    payload.setAdminId(adminId);
                    Room result = null;
                    if (payload != null) {
                        result = this.usersController.regRoom(payload);
                    }
                    if (result != null){
                        resp.setHeader("Access-Control-Allow-Origin", "*");
                        resp.setStatus(HttpServletResponse.SC_ACCEPTED);
                    } else {
                        resp.setHeader("Access-Control-Allow-Origin", "*");
                        resp.setStatus(403);
                    }
                }
            }
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        PrintWriter out = resp.getWriter();
        String body = req.getReader().lines().collect(Collectors.joining());
        if (!"application/json".equalsIgnoreCase(req.getHeader("Content-Type"))) {
            resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE, "Invalid content type");
        } else {
            String url = req.getRequestURI();
            if (url.equals("/chat/myaccount")) {
                String token = req.getHeader("Authorization");
                Token t = TokenProvider.decode(token);
                if (TokenProvider.checkToken(t)) {
                    UserRegDto payload = JsonHelper.fromJson(body, UserRegDto.class).orElseThrow(BadRequest::new);
                    if (payload != null) {
                        payload.setId(t.getId());
                        payload.setVerification(true);
                        this.usersController.update(payload);
                        System.out.println(200);
                        resp.setHeader("Access-Control-Allow-Origin", "*");
                        resp.setStatus(200);
                    } else {
                        System.out.println(403);
                        resp.setHeader("Access-Control-Allow-Origin", "*");
                        resp.setStatus(403);
                        out.write("No data to update!");
                    }
                } else {
                    resp.setHeader("Access-Control-Allow-Origin", "*");
                    resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    out.write("Expired token!");
                }
            }
        }
    }

    private String httpServletRequestToString(HttpServletRequest request) {
        StringBuilder sb = new StringBuilder();

        sb.append("Request Method = [" + request.getMethod() + "], ");
        sb.append("Request URL Path = [" + request.getRequestURL() + "], ");

        String headers =
                Collections.list(request.getHeaderNames()).stream()
                        .map(headerName -> headerName + " : " + Collections.list(request.getHeaders(headerName)) )
                        .collect(Collectors.joining(", "));

        if (headers.isEmpty()) {
            sb.append("Request headers: NONE,");
        } else {
            sb.append("Request headers: ["+headers+"],");
        }

        String parameters =
                Collections.list(request.getParameterNames()).stream()
                        .map(p -> p + " : " + Arrays.asList( request.getParameterValues(p)) )
                        .collect(Collectors.joining(", "));

        if (parameters.isEmpty()) {
            sb.append("Request parameters: NONE.");
        } else {
            sb.append("Request parameters: [" + parameters + "].");
        }

        return sb.toString();
    }
}
