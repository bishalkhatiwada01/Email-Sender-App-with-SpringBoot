package com.example.emailsender.EmailSender.services.impl;

import com.example.emailsender.EmailSender.services.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

@Service
public class EmailServiceImpl implements EmailService {

    String myEmail = "bishalkhatiwada444@gmail.com";

    private JavaMailSender mailSender;

    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    private Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Override
    public void sendEmail(String to, String subject, String message) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);
        simpleMailMessage.setFrom(myEmail);
        mailSender.send(simpleMailMessage);
        logger.info("Email has been sent");
    }

    @Override
    public void sentEmail(String[] to, String subject, String message) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);
        simpleMailMessage.setFrom(myEmail);
        mailSender.send(simpleMailMessage);
        logger.info("Mail sent successfully");
    }

    @Override
    public void sendEmailWithHtml(String to, String subject, String htmlContent) {
        MimeMessage simpleMailMessage = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(simpleMailMessage, true, "UTF-8");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setFrom(myEmail);
            helper.setText(htmlContent, true);
            mailSender.send(simpleMailMessage);
            logger.info("Mail sent successfully");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendEmailWithFile(String to, String subject, String message, File file) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(message, true);
            helper.setFrom(myEmail);
            FileSystemResource fileSystemResource = new FileSystemResource(file);
            helper.addAttachment(fileSystemResource.getFilename(), file);
            mailSender.send(mimeMessage);
            logger.info("-----------Sent mail successfully------------");


        } catch (MessagingException e) {
            throw new RuntimeException(e);

        }

    }

    @Override
    public void sendEmailWithFile(String to, String subject, String message, InputStream is) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(message, true);
            helper.setFrom(myEmail);
            File file = new File("test.png");
            Files.copy(is, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
            FileSystemResource fileSystemResource = new FileSystemResource(file);
            helper.addAttachment(fileSystemResource.getFilename(), file);

            mailSender.send(mimeMessage);
            logger.info("-----------Sent mail successfully------------");

        } catch (MessagingException e) {
            throw new RuntimeException(e);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
