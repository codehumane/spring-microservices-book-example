package codehumane.legacyrest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Slf4j
@RestController
public class HomeController {

    private final Environment env;

    public HomeController(Environment env) {
        this.env = env;
    }

    @RequestMapping("/greeting")
    @ResponseBody
    public HttpEntity<Greet> greeting(
            @RequestParam(
                    value = "name",
                    required = false,
                    defaultValue = "HATEOAS"
            ) String name
    ) {

        Greet greet = newGreet(name);
        greet.add(linkTo(methodOn(HomeController.class).greeting(name)).withSelfRel());
        return new ResponseEntity<>(greet, HttpStatus.OK);
    }

    private Greet newGreet(String name) {
        log.info("bootrest.customproperty "+ env.getProperty("bootrest.customproperty"));
        return new Greet("Hello " + name);
    }

    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    static class Greet extends ResourceSupport {

        private String message;

    }
}