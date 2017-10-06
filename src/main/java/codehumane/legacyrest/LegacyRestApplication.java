package codehumane.legacyrest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@EnableAuthorizationServer
@EnableResourceServer
@SpringBootApplication
public class LegacyRestApplication implements CommandLineRunner {

    @Autowired
    private Sender sender;

    public static void main(String[] args) {
        SpringApplication.run(LegacyRestApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        sender.send("Hello Messaging...!!!");
    }
}
