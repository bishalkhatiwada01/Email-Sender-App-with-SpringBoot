package com.example.emailsender.EmailSender.helper;

import lombok.*;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CustomResponse {

    private String message;

    private HttpStatus httpStatus;

    @Builder.Default
    private boolean success = false;

}
