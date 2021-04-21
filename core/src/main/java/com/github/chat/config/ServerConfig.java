package com.github.chat.config;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import javax.servlet.ServletException;
import java.io.File;

public class ServerConfig {

    public static Tomcat start() throws ServletException, LifecycleException {
        Tomcat tomcat = new Tomcat();

        String webPort = System.getenv("PORT");
        if (webPort == null || webPort.isEmpty()) {
            webPort = "8081";
        }

        tomcat.setPort(Integer.parseInt(webPort));

        Context ctx = tomcat.addWebapp("/", new File(".").getAbsolutePath());
        tomcat.addServlet("","UserHandler",HandlerConfig.usersHandler());
        ctx.addServletMappingDecoded("/*", "UserHandler");
        return tomcat;
    }
}
