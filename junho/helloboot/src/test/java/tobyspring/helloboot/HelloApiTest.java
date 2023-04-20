package tobyspring.helloboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

class HelloApiTest {

	@Test
	void helloApi() {
		TestRestTemplate restTemplate = new TestRestTemplate();
		ResponseEntity<String> spring = restTemplate.getForEntity(
				"http://localhost:8080/hello?name={name}", String.class, "spring");

		//status code 200인가?
		Assertions.assertThat(spring.getStatusCode()).isEqualTo(HttpStatus.OK);
		//header의 콘텐츠 타입이 text/plain인가?
		Assertions.assertThat(spring.getHeaders().getFirst(HttpHeaders.CONTENT_TYPE)).startsWith(MediaType.TEXT_HTML_VALUE + ";charset=utf-8");
		//body가 안녕 spring인가?
		Assertions.assertThat(spring.getBody()).isEqualTo("안녕spring");
	}

	@Test
	void failedHelloApi() {
		TestRestTemplate restTemplate = new TestRestTemplate();
		ResponseEntity<String> spring = restTemplate.getForEntity(
				"http://localhost:8080/hello?name=", String.class);

		Assertions.assertThat(spring.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
