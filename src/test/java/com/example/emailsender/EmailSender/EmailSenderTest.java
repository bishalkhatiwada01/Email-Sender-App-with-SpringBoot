package com.example.emailsender.EmailSender;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.emailsender.EmailSender.services.EmailService;


@SpringBootTest

public class EmailSenderTest {
    @Autowired
    private EmailService emailService;

    @Test
    void emailSendTest() {
        System.out.println("Sending Email...........");
        emailService.sendEmail("bishalkhatiwada13@gmail.com", "Email from spring boot", "This email is sent test wether the email is sent or not");
    }
}
