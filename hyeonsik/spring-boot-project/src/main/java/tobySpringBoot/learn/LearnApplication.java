package tobySpringBoot.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@ComponentScan  // 이 패키지부터 하위 패키지까지 모든 @Component 를 스캔해서 빈으로 등록해 준다.
public class LearnApplication {

	public static void main(String[] args) {
		// SpringContainer를 만든다.
		AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext() {
			@Override
			protected void onRefresh() {  // onRefresh는 applicationContext.refresh() 내부에서 호출된다.
				super.onRefresh();

				ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();  // Factory에 기본적인 설정들이 잡혀있다.
				WebServer webServer = serverFactory.getWebServer(servletContext -> {
					servletContext.addServlet("dispatcherServlet",
							new DispatcherServlet(this)
					).addMapping("/*");  // path pattern matching
				});
				webServer.start();
			}
		};

		applicationContext.register(LearnApplication.class);  // 구성정보를 담고있는 클래스를 불러와줘야한다.
		applicationContext.refresh();  // 이걸 통해 빈 오브젝트들을 만들어준다.
	}

}
