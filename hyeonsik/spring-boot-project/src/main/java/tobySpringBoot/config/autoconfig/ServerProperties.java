package tobySpringBoot.config.autoconfig;

import org.springframework.beans.factory.annotation.Value;
import tobySpringBoot.config.MyConfigurationProperties;

// 그저 데이터를 가지고 있는 데이터 홀더 클래스임 -> 그러니 그냥 게터세터로
@MyConfigurationProperties(prefix = "server")
public class ServerProperties {
    private String contextPath;

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
