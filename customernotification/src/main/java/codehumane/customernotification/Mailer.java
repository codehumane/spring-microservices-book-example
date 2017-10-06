package codehumane.customernotification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
class Mailer {

    private final JavaMailSender javaMailService;

    public Mailer(JavaMailSender javaMailService) {
        this.javaMailService = javaMailService;
    }

    public void sendMail(String email) {
        final SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setSubject("Registration");
        mailMessage.setText("Successfully Registered");
        javaMailService.send(mailMessage);
    }
}
