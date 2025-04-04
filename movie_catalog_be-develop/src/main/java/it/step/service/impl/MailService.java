package it.step.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSenderImpl mailSender;

    void sendEmail(String email, String token) throws MessagingException {
        MimeMessage msg = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg, "utf-8");

        mailSender.setHost("sandbox.smtp.mailtrap.io");
        mailSender.setPort(587);
        mailSender.setUsername("7ad41d74460fc7");
        mailSender.setPassword("05d1f690983731");

        helper.setFrom("noreply@moviecatalog.it");
        helper.setTo(email);
        helper.setSubject("Password Recovery");

        String link = "http://localhost:4200/forgot-password/"+ token;

        String html = "<h1>You are almost done!</h1>" +
                        "reset your password here: " +
                        "<a href='" + link + "'>Click Me!</a>";

        helper.setText(html, true);

        mailSender.send(msg);
    }

}
