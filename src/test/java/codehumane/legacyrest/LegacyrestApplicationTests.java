package codehumane.legacyrest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class LegacyrestApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Test
    public void testVanillaService() {
        final RestTemplate restTemplate = new RestTemplate();
        Greet greet = restTemplate.getForObject("http://localhost:8080", Greet.class);
        assertEquals("Hello World!", greet.getMessage());
    }

    @Test
    public void testSecureService() {
        String plainCreds = "guest:guest123";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + new String(Base64.encode(plainCreds.getBytes())));
        HttpEntity<String> request = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Greet> response = restTemplate
                .exchange("http://localhost:9090/greeting?name=World!", HttpMethod.GET, request, Greet.class);
        assertEquals("Hello World!", response.getBody().getMessage());
    }
}
