package tobySpringBoot.learn;

import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class MySpringApplication {

    public static void run(Class<?> applicationClass, String... args) {
        // SpringContainer를 만든다.
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext() {
            @Override
            protected void onRefresh() {  // onRefresh는 applicationContext.refresh() 내부에서 호출된다.
                super.onRefresh();

                ServletWebServerFactory serverFactory = this.getBean(ServletWebServerFactory.class);
                DispatcherServlet dispatcherServlet = this.getBean(DispatcherServlet.class);
                // dispatcherServlet.setApplicationContext(this);  // SrpingContainer를 연결해준다.
                // 근데 여기에 주입 구문을 안넣어줘도 정상적으로 동작한다. 왜? SpringContainer가 알아서 주입해줌!
                // 이걸 이해하려면 Bean의 LifeCycle Method라는 개념을 알아야함

                WebServer webServer = serverFactory.getWebServer(servletContext -> {
                    servletContext.addServlet("dispatcherServlet", dispatcherServlet).addMapping("/*");  // path pattern matching
                });
                webServer.start();
            }
        };

        applicationContext.register(applicationClass);  // 구성정보를 담고있는 클래스를 불러와줘야한다.
        applicationContext.refresh();  // 이걸 통해 빈 오브젝트들을 만들어준다.
    }
}
