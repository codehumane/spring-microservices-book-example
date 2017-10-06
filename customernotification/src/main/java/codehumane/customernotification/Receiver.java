package codehumane.customernotification;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Receiver {

    private final Mailer mailer;

    public Receiver(Mailer mailer) {
        this.mailer = mailer;
    }

    @Bean
    Queue queue() {
        return new Queue("CustomerQ", false);
    }

    @RabbitListener(queues = "CustomerQ")
    public void processMessage(String email) {
        log.info("Received from queue: {}", email);
        mailer.sendMail(email);
    }
}
