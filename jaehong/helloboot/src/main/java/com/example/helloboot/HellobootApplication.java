package com.example.helloboot;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;

public class HellobootApplication {
    public static void main(String[] args) {
        final var tomcatServletWebServerFactory = new TomcatServletWebServerFactory();
        final var webServer = tomcatServletWebServerFactory.getWebServer(servletContext -> {
            servletContext.addServlet("hello", new HttpServlet() {
                @Override
                protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                    response.setStatus(200);
                    response.setHeader("Content-Type", "text/plain");
                    response.getWriter().println("Hello Servlet");
                }
            }).addMapping("/hello");
        });
        webServer.start();
    }

}
