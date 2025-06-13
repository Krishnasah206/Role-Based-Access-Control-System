package com.krishna.journalService.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTests {

    @Autowired
    private EmailService emailService;

    @Disabled
    @Test
    void testSendMail() {
        emailService.sendEmail(
                "krishnasah04873@gmail.com",
                "Testing Java Mail Sender",
                "Hi, aap Kaise Hain?"
        );
    }
}
