package com.github.chat.utils;

import com.github.chat.handlers.WebsocketHandler;
import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;

import javax.websocket.DeploymentException;
import javax.websocket.server.ServerContainer;
import javax.websocket.server.ServerEndpointConfig;

public class WrapTomcat extends Tomcat {

    private Tomcat tomcat;

    private Context ctx;


    public void initCtx(Context ctx) {
        this.ctx = ctx;
    }

    public void websocketRegistry(Object handler) {
        ServerContainer scon = (ServerContainer) ctx.getServletContext().getAttribute(ServerContainer.class.getName());

        try {
            scon.addEndpoint(ServerEndpointConfig
                    .Builder
                    .create(WebsocketHandler.class, "/echo")
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
    }
}
