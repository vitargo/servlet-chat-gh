package com.github.chat.config;

import com.github.chat.handlers.WebsocketHandler;
import com.github.chat.network.Broker;
import com.github.chat.network.WebsocketConnectionPool;
import com.github.chat.utils.ServerRunner;
import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;

import javax.servlet.ServletException;
import javax.websocket.DeploymentException;
import javax.websocket.server.ServerContainer;
import javax.websocket.server.ServerEndpointConfig;
import java.io.File;
import java.util.List;
import java.util.function.Consumer;

public class ServerConfig {

    public static ServerRunner start() throws ServletException {
        Tomcat tomcat = new Tomcat();

        String webPort = System.getenv("PORT");
        if (webPort == null || webPort.isEmpty()) {
            webPort = "8081";
        }

        tomcat.setPort(Integer.parseInt(webPort));

        File f = new File("core/web");
        Context ctx = tomcat.addWebapp("", f.getAbsolutePath());
        return new ServerRunner(tomcat, ctx, List.of(websocketHandler));
    }

    private static Consumer<Context> websocketHandler = ctx -> {
        WebsocketHandler handler = new WebsocketHandler(new WebsocketConnectionPool(), new Broker());
        ServerContainer scon = (ServerContainer) ctx.getServletContext().getAttribute(ServerContainer.class.getName());
        try {
            scon.addEndpoint(ServerEndpointConfig
                    .Builder
                    .create(WebsocketHandler.class, "/chat")
                    .configurator(new ServerEndpointConfig.Configurator() {
                        @Override
                        public <T> T getEndpointInstance(Class<T> clazz) throws InstantiationException {
                            return (T) handler;
                        }
                    })
                    .build());
        } catch (final DeploymentException e) {
            e.printStackTrace();
        }
    };
}
