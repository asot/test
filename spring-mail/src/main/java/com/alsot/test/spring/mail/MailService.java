package com.alsot.test.spring.mail;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${mail.sender.username}")
    private String senderAddress;

    @Value("${mail.recipient.address}")
    private String recipientAddress;

    private static final String HTML_TEXT = "<html><body><h1>Hello World!!!</h1><img src=\"cid:imgAttachment\"/></body></html>";

    public void sendSimpleMail() {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom(senderAddress);
        msg.setTo(recipientAddress);
        msg.setSubject("hello world message");
        msg.setText("Hello World!");
        mailSender.send(msg);
    }

    public void sendRichMail() {
        MimeMessage msg = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(msg, true);
            helper.setFrom(senderAddress);
            helper.setTo(recipientAddress);
            helper.setSubject("spring io image");

            helper.setText(HTML_TEXT, true);
            ClassPathResource img = new ClassPathResource("com/alsot/test/spring/mail/img/spring-logo.png");
            helper.addInline("imgAttachment", img);
        } catch (MessagingException me) {
            throw new RuntimeException(me);
        }

        mailSender.send(msg);
    }

}
