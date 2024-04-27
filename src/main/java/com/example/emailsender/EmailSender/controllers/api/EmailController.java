package com.example.emailsender.EmailSender.controllers.api;

import com.example.emailsender.EmailSender.helper.CustomResponse;
import com.example.emailsender.EmailSender.helper.EmailRequest;
import com.example.emailsender.EmailSender.services.EmailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequestMapping("/api/v1/email")
public class EmailController {

    private EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }


    // send email
    @PostMapping("/send")
    public ResponseEntity<?> sendEmail(@RequestBody EmailRequest request) {
        emailService.sendEmailWithHtml(request.getTo(), request.getSubject(), request.getMessage());
        return ResponseEntity.ok(CustomResponse.builder().message("Email sent successfully").httpStatus(HttpStatus.OK).success(true).build());
    }

    @PostMapping("/send-with-file")
    public ResponseEntity<CustomResponse> sendWithFile(@RequestBody EmailRequest request, @RequestParam MultipartFile file) throws IOException {
        emailService.sendEmailWithFile(request.getTo(), request.getSubject(), request.getMessage(), file.getInputStream());
        return ResponseEntity.ok(CustomResponse.builder().message("Email sent successfully").httpStatus(HttpStatus.OK).success(true).build());

    }

}
