package com.example.helloboot;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

class HelloApiTest {

    @Test
    void hello_test() {
        final var rest = new TestRestTemplate();
        final var response = rest.getForEntity("http://localhost:8080/hello?name={name}", String.class, "Spring");

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo("*hello Spring*");
        assertThat(response.getHeaders().getFirst(HttpHeaders.CONTENT_TYPE)).startsWith(TEXT_PLAIN_VALUE);
    }

    @Test
    void fail_test() {
        final var rest = new TestRestTemplate();
        final var response = rest.getForEntity("http://localhost:8080/hello?name=", String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}