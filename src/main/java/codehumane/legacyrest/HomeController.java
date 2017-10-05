package codehumane.legacyrest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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

@RestController
public class HomeController {

    @RequestMapping("/greeting")
    @ResponseBody
    public HttpEntity<Greet> greeting(
            @RequestParam(
                    value = "name",
                    required = false,
                    defaultValue = "HATEOAS"
            ) String name
    ) {

        Greet greet = new Greet("Hello " + name);
        greet.add(linkTo(methodOn(HomeController.class).greeting(name)).withSelfRel());
        return new ResponseEntity<>(greet, HttpStatus.OK);
    }
}

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
class Greet extends ResourceSupport {

    private String message;

}