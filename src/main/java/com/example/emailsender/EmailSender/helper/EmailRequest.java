package com.example.emailsender.EmailSender.helper;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class EmailRequest {

    private String to;
    private String subject;
    private String message;
}
