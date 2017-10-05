package codehumane.legacyrest;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @RequestMapping("/")
    public Greet sayHello() {
        return new Greet("Hello World!");
    }
}

class Greet {

    @Getter
    @Setter
    private String message;

    public Greet(String message) {
        this.message = message;
    }
}