package codehumane.legacyrest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
class Greet {

    private String message;

}