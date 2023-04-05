package com.example.helloboot;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

public class HellobootApplication {
    public static void main(String[] args) {
        final var tomcatServletWebServerFactory = new TomcatServletWebServerFactory();
        final var webServer = tomcatServletWebServerFactory.getWebServer(servletContext -> {
            servletContext.addServlet("hello", new HttpServlet() {
                @Override
                protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                    final var name = request.getParameter("name");
                    response.setStatus(HttpStatus.OK.value());
                    response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);
                    response.getWriter().println("Hello" + name);
                }
            }).addMapping("/hello");
        });
        webServer.start();
    }

}
