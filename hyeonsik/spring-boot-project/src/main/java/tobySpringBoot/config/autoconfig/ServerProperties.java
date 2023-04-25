package tobySpringBoot.config.autoconfig;

import org.springframework.beans.factory.annotation.Value;

// 그저 데이터를 가지고 있는 데이터 홀더 클래스임 -> 그러니 그냥 게터세터로
public class ServerProperties {
    @Value("${contextPath}")  // BeanFactoryPostProcessor 라는 후처리 로직(이것도 Bean임)이 동작을 처리해준다!
    private String contextPath;

    @Value("${port:8080}")  // port가 없을경우 :뒤에 있는 8080을 기본값으로 사용한다.
    private int port;

    public String getContextPath() {
        return contextPath;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
