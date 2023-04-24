package tobyspring.config.autoconfig;

import tobyspring.config.MyConfigurationProperties;

@MyConfigurationProperties(prefix = "server")
public class ServerProperties {

    private String contextPath;
    private int port;

    public String getContextPath() {
        return contextPath;
    }

    public void setContextPath(final String contextPath) {
        this.contextPath = contextPath;
    }

    public int getPort() {
        return port;
    }

    public void setPort(final int port) {
        this.port = port;
    }

}
