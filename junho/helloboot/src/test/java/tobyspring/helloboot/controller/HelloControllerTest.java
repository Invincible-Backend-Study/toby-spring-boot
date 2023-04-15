package tobyspring.helloboot.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class HelloControllerTest {
    @Test
    void helloControllerTest(){
        HelloController helloController = new HelloController(name -> name);

        String ret = helloController.hello("TEST");

        Assertions.assertThat(ret).isEqualTo("TEST");
    }

    @Test
    void helloControllerExceptionTest(){
        HelloController helloController = new HelloController(name -> name);

        Assertions.assertThatThrownBy(() -> {
            String ret = helloController.hello(null);
        }).isInstanceOf(IllegalArgumentException.class);
    }


    @Test
    void helloControllerExceptionTestByString(){
        HelloController helloController = new HelloController(name -> name);

        Assertions.assertThatThrownBy(() -> {
            String ret = helloController.hello("");
        }).isInstanceOf(IllegalArgumentException.class);
    }

}