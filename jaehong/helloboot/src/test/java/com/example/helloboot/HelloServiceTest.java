package com.example.helloboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class HelloServiceTest {


    @Test
    void success_test() {
        final var sampleHello = new SimpleHelloService();
        final var result = sampleHello.sayHello("name");

        Assertions.assertThat(result).isEqualTo("hello name");
    }
}