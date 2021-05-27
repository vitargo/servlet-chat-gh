package com.github.chat.config;

import com.github.chat.handlers.PrivateWebsocketHandler;
import com.github.chat.handlers.WebsocketHandler;
import com.github.chat.network.Broker;
import com.github.chat.network.WebsocketConnectionPool;
import com.github.chat.utils.ServerRunner;
import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.websocket.server.WsContextListener;

import javax.servlet.ServletException;
import javax.websocket.DeploymentException;
import javax.websocket.server.ServerContainer;
import javax.websocket.server.ServerEndpointConfig;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class ServerConfig {

    private static Map<String, Consumer<Context>> listHandlers = new HashMap<>();

    public static ServerRunner start() throws ServletException {
        Tomcat tomcat = new Tomcat();

        String webPort = System.getenv("PORT");
        if (webPort == null || webPort.isEmpty()) {
            webPort = "8081";
        }

        tomcat.setPort(Integer.parseInt(webPort));

        tomcat.getHost().setAppBase(".");

        File f = new File("core/web");
        Context ctx = tomcat.addWebapp("", f.getAbsolutePath());
        tomcat.addServlet("", "UserHandler", HandlerConfig.usersHandler()).setAsyncSupported(true);
        ctx.addServletMappingDecoded("/chat/*", "UserHandler");
        ctx.addApplicationListener(WsContextListener.class.getName());
        return new ServerRunner(tomcat, ctx, List.of(websocketHandler, listHandlers.get("websocketHandlerPrivate")));
    }

    public static void createNewRoom(long idRoom) {
        Consumer<Context> websocketHandlerPrivate = ctx -> {
            PrivateWebsocketHandler handler = new PrivateWebsocketHandler(new WebsocketConnectionPool(), new Broker());
            ServerContainer scon = (ServerContainer) ctx.getServletContext().getAttribute(ServerContainer.class.getName());
            try {
                scon.addEndpoint(ServerEndpointConfig
                        .Builder
                        .create(PrivateWebsocketHandler.class, ("/room" + idRoom))
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
        listHandlers.put(("handler" + idRoom), websocketHandlerPrivate);
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


    Consumer<Context> websocketHandlerCreate = ctx -> {
        PrivateWebsocketHandler handler = new PrivateWebsocketHandler(new WebsocketConnectionPool(), new Broker());
        ServerContainer scon = (ServerContainer) ctx.getServletContext().getAttribute(ServerContainer.class.getName());
        try {
            scon.addEndpoint(ServerEndpointConfig
                    .Builder
                    .create(PrivateWebsocketHandler.class, ("/room" + idRoom))
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
}
