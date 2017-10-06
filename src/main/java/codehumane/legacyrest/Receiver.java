package codehumane.legacyrest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Receiver {

    @RabbitListener(queues = "TestQ")
    public void processMessage(String content) {
        log.info(content);
    }
}
