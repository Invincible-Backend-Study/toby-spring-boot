package tobySpringBoot.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LearnApplication {

	public static void main(String[] args) {
		// SpringContainer를 만든다.
		GenericWebApplicationContext applicationContext = new GenericWebApplicationContext();
		applicationContext.registerBean(HelloController.class);  // 생성할 빈들을 명시
		applicationContext.registerBean(SimpleHelloService.class);
		applicationContext.refresh();  // 이걸 통해 빈 오브젝트들을 만들어준다.

		ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();  // Factory에 기본적인 설정들이 잡혀있다.
		WebServer webServer = serverFactory.getWebServer(servletContext -> {
			servletContext.addServlet("dispatcherServlet",
							new DispatcherServlet(applicationContext)
					).addMapping("/*");  // path pattern matching
		});
		webServer.start();
	}

}
