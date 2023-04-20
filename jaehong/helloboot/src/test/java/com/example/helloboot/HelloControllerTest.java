package com.example.helloboot;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;


class HelloControllerTest {


    @Test
    void success_test() {
        final var helloController = new HelloController(name -> name);
        final var result = helloController.hello("spring");

        Assertions.assertThat(result).isEqualTo("spring");
    }

    @Test
    void fail_test() {
        final var helloController = new HelloController(name -> null);

        assertThatIllegalArgumentException().isThrownBy(() -> helloController.hello(null));
        assertThatIllegalArgumentException().isThrownBy(() -> helloController.hello(""));
    }
}