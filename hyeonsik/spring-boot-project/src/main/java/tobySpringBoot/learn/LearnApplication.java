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

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LearnApplication {

	public static void main(String[] args) {
		// SpringContainer를 만든다.
		GenericApplicationContext applicationContext = new GenericApplicationContext();
		applicationContext.registerBean(HelloController.class);  // 생성할 빈들을 명시
		applicationContext.registerBean(SimpleHelloService.class);
		applicationContext.refresh();  // 이걸 통해 빈 오브젝트들을 만들어준다.

		ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();  // Factory에 기본적인 설정들이 잡혀있다.
		WebServer webServer = serverFactory.getWebServer(servletContext -> {
			servletContext.addServlet("hello", new HttpServlet() {
				@Override
				protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
					// 인증, 보안, 다국어, 공통 기능 등에 대한 처리가 들어가야함
					if (req.getRequestURI().equals("/hello") && req.getMethod().equals(HttpMethod.GET.name())) {
						// 파라미터 분석
						String name = req.getParameter("name");

						HelloController helloController = applicationContext.getBean(HelloController.class);  // 등록된 빈을 가져온다.
						String ret = helloController.hello(name);

						// response 를 만든다.
						resp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);
						resp.setContentType(MediaType.TEXT_PLAIN_VALUE);
						resp.getWriter().print(ret);
					}
					else {
						resp.setStatus(HttpStatus.NOT_FOUND.value());
					}
				}
			}).addMapping("/*");  // path pattern matching
		});
		webServer.start();
	}

}
