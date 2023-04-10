package tobyspring.helloboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration
public class HellobootApplication {

    // 스프링이 파라미터를 보고 자동으로 주입하고 호출한다.
    @Bean
    public HelloController helloController(final HelloService helloService) {
        return new HelloController(helloService);
    }

    @Bean
    public HelloService helloService() {
        return new SimpleHelloService();
    }

    public static void main(String[] args) {
        // ApplicationContext는 애플리케이션을 구성하는 많은 정보, 리소스에 접근하는 방법, 내부 이벤트 전달, 구독방법 등 많은 작업들을 수행하는 기능들을 담고있다.
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext() {
            @Override
            protected void onRefresh() {
                super.onRefresh();

                ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
                // 톰캣외에 제티, 언더토우 같은 유명한 서블릿 컨테이너를 지원할 수 있고, 일관된 방식으로 동작하도록 추상화했기에 Tomcat이 보이지 않음
                WebServer webServer = serverFactory.getWebServer(servletContext -> {
                    servletContext.addServlet("dispatcherServlet", new DispatcherServlet(this))
                            .addMapping("/*");
                });
                webServer.start();

            }
        };
        applicationContext.register(HellobootApplication.class);
        // 자기가 가지고 있는 정보를 활용해 컨텍스트를 초기화
        applicationContext.refresh();

    }

}
