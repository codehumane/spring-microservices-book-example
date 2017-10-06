package codehumane.legacyrest;

import codehumane.legacyrest.HomeController.Greet;
import lombok.val;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class HomeControllerTest {

    @Test
    public void greeting_쿼리문자열에_입력된_값이_반환값에_포함되야_한다() {

        // given
        val headers = new HttpHeaders();
        headers.add("Authorization", generateCredential());
        val request = new HttpEntity<String>(headers);
        val restTemplate = new RestTemplate();

        // when
        val greet = restTemplate
                .exchange("http://localhost:9090/greeting?name=Gunhee", HttpMethod.GET, request, Greet.class);

        // then
        assertEquals("Hello Gunhee", greet.getBody().getMessage());
    }

    @Test(expected = HttpClientErrorException.class)
    public void greeting_올바르지_않은_인증정보의_요청에는_401을_응답한다() {

        // given
        val entity = new HttpEntity<>(new HttpHeaders());
        val restTemplate = new RestTemplate();

        // when
        val response = restTemplate
                .exchange("http://localhost:9090/greeting?name=Gunhee", HttpMethod.GET, entity, Greet.class);

        // then
        assertEquals(401, response.getStatusCode().value());
    }

    private String generateCredential() {
        return "Basic " + new String(Base64.encode("guest:guest123".getBytes()));
    }
}