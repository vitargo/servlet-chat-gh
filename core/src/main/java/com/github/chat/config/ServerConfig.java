package com.github.chat.config;

import com.github.chat.handlers.WebsocketHandler;
import com.github.chat.utils.WrapTomcat;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.websocket.ClientEndpointConfig;
import javax.websocket.DeploymentException;
import javax.websocket.server.ServerContainer;
import javax.websocket.server.ServerEndpointConfig;
import java.io.File;
import java.net.http.WebSocket;
import java.util.HashSet;
import java.util.Set;

public class ServerConfig {

    public static WrapTomcat start() throws ServletException, LifecycleException {
        WrapTomcat tomcat = new WrapTomcat();

        String webPort = System.getenv("PORT");
        if (webPort == null || webPort.isEmpty()) {
            webPort = "8080";
        }

        tomcat.setPort(Integer.parseInt(webPort));

        Context ctx = tomcat.addWebapp("/", new File(".").getAbsolutePath());
        tomcat.initCtx(ctx);
        tomcat.addServlet("", "UserHandler", HandlerConfig.usersHandler());
        ctx.addServletMappingDecoded("/*", "UserHandler");

        return tomcat;
    }

    public static ServerEndpointConfig.Configurator sec = new ServerEndpointConfig.Configurator() {
        @Override
        public <T> T getEndpointInstance(Class<T> clazz) throws InstantiationException {
            return (T) new WebsocketHandler();
        }
    };
}
