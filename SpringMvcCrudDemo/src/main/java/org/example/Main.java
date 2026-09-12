package org.example;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.example.config.WebConfig;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.io.File;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() throws LifecycleException {

        //Setting up tomcat at port 8080 and using connector to listed to the port

        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        tomcat.getConnector();

        //Multiple apps can be handles by a tomcat so we need to specify the following steps to tell which app we are running
        //This step was used in Servlets, and it is mandatory while making the spring MVC application

        String contextPath = "";

        String baseDoc = new File("src/main/webapp").getAbsolutePath();

        Context context = tomcat.addContext(contextPath, baseDoc);

        //IOC Container UP

        AnnotationConfigWebApplicationContext springContext = new AnnotationConfigWebApplicationContext();

        springContext.register(WebConfig.class);

        //Dispatcher Servlet

        DispatcherServlet dispatcherServlet = new DispatcherServlet((springContext));

        Tomcat.addServlet(context, "dispatcherServlet", dispatcherServlet);

        context.addServletMappingDecoded("/", "dispatcherServlet");

        tomcat.start();

        System.out.println("Server starter at port : 8080");

        tomcat.getServer().await();
    }
}
