package tobyspring.helloboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

public class HelloController {
    public String hello(String name) {
        if(name == null){
            name = "";
        }
        return "안녕" + name;
    }
}
