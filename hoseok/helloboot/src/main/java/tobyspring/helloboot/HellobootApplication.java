package tobyspring.helloboot;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;

public class HellobootApplication {

    public static void main(String[] args) {
        ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();

        // 톰캣외에 제티, 언더토우 같은 유명한 서블릿 컨테이너를 지원할 수 있고, 일관된 방식으로 동작하도록 추상화했기에 Tomcat이 보이지 않음
        WebServer webServer = serverFactory.getWebServer(servletContext -> {
            servletContext.addServlet("hello", new HttpServlet() {
				@Override
				protected void service(final HttpServletRequest req, final HttpServletResponse resp)
						throws ServletException, IOException {
                    resp.setStatus(200);
                    resp.setHeader("Content-Type", "text/plain");
                    resp.getWriter().print("hello");
                }
            }).addMapping("/hello");
        });
        webServer.start();
    }

}
