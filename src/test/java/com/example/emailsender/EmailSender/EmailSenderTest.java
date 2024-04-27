package com.example.emailsender.EmailSender;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.emailsender.EmailSender.services.EmailService;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;


@SpringBootTest

public class EmailSenderTest {
    @Autowired
    private EmailService emailService;

    @Test
    void emailSendTest() {
        System.out.println("Sending Email...........");
        emailService.sendEmail("bishalkhatiwada13@gmail.com", "Email from spring boot", "This email is sent test wether the email is sent or not");
    }

    @Test
    void sendHtmlInEmail() {
        String html = "" + "<h1 style=\"color: #333; font-size: 24px; margin-bottom: 10px;\">Welcome to My Sample Page</h1>\n" + "<p style=\"color: #666; line-height: 1.6; margin-bottom: 20px;\">This is a small sample of HTML code.</p>\n" + "<h2 style=\"color: #333; font-size: 20px; margin-bottom: 10px;\">Additional Heading</h2>\n" + "<p style=\"color: #666; line-height: 1.6;\">This is an additional paragraph added to the sample page.</p>\n";
        emailService.sendEmailWithHtml("bishalkhatiwada13@gmail.com", "sending email with html", html);
    }

    @Test
    void sentFileInEmail() {
        emailService.sendEmailWithFile("bishalkhatiwada13@gmail.com", "sending email with FILE", "This email contains file", new File("/Users/bishalkhatiwada/Downloads/WhatsApp Image 2023-12-19 at 2.15.18 PM.jpeg"));
    }

    @Test
    void sentFileInEmaiWithStream() {
        File file = new File("/Users/bishalkhatiwada/Downloads/WhatsApp Image 2023-12-19 at 2.15.18 PM.jpeg");
        try {
            InputStream is = new FileInputStream(file);
            emailService.sendEmailWithFile("bishalkhatiwada13@gmail.com", "sending email with FILE", "This email contains file", is);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
