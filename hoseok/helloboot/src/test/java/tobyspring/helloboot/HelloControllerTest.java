package tobyspring.helloboot;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HelloControllerTest {

    @Test
    void helloController() {
        HelloController helloController = new HelloController(name -> name);

        String ret = helloController.hello("Test");

        assertThat(ret).isEqualTo("Test");
    }

    @Test
    void failHelloController() {
        HelloController helloController = new HelloController(name -> name);

        Assertions.assertAll(
                () -> assertThatThrownBy(() -> helloController.hello(null))
                        .isInstanceOf(IllegalArgumentException.class),
                () -> assertThatThrownBy(() -> helloController.hello(""))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

}