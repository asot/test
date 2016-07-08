package com.alsot.test.spring.mail;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:com/alsot/test/spring/mail/config/config.xml")
public class MailServiceTest {

    @Autowired
    private MailService mailService;

    @Ignore
    @Test
    public void sendSimpleMail() {
        mailService.sendSimpleMail();
    }

    @Test
    public void sendRichMail() {
        mailService.sendRichMail();
    }

}
