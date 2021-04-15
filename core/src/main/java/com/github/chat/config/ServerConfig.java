package com.github.chat.config;

import com.github.chat.controllers.UsersController;
import com.github.chat.handlers.UsersHandler;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

import javax.servlet.ServletException;
import java.io.File;

public class ServerConfig {
    public static Tomcat start() throws ServletException, LifecycleException {
        Tomcat tomcat = new Tomcat();

        String webPort = System.getenv("PORT");
        if (webPort == null || webPort.isEmpty()) {
            webPort = "8080";
        }

        tomcat.setPort(Integer.valueOf(webPort));

        Context ctx = tomcat.addWebapp("/", new File(".").getAbsolutePath());
        tomcat.addServlet("","UserHandler",new UsersHandler(new UsersController()));
        ctx.addServletMappingDecoded("/*", "UserHandler");
        return tomcat;
    }
}
