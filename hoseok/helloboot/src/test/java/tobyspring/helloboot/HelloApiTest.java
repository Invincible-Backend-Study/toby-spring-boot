package tobyspring.helloboot;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

public class HelloApiTest {

    @Test
    void helloApi() {
        // http localhost:8080/hello?name=spring
        TestRestTemplate rest = new TestRestTemplate();

        ResponseEntity<String> spring = rest.getForEntity("http://localhost:8080/hello?name={name}", String.class,
                "Spring");

        // 응답 검증 3가지
        Assertions.assertAll(
                // status code 200
                () -> assertThat(spring.getStatusCode()).isEqualTo(HttpStatus.OK),
                // header(Content-Type) text/plain
                () -> assertThat(spring.getHeaders().getFirst(HttpHeaders.CONTENT_TYPE)).startsWith(
                        MediaType.TEXT_PLAIN_VALUE),
                // body Hello Spring
                () -> assertThat(spring.getBody()).isEqualTo("Hello Spring")
        );
    }

    @Test
    void failsHelloApi() {
        // http localhost:8080/hello?name=spring
        TestRestTemplate rest = new TestRestTemplate();

        ResponseEntity<String> spring = rest.getForEntity("http://localhost:8080/hello", String.class);

        // 응답 검증 3가지
        assertThat(spring.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
