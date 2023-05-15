package com.scott.sideproject.linebotbackend.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiError {
    private HttpStatus status;
    private int code;
    private String message;
    private String path;

    public ApiError(HttpStatus status, String message, String path) {
        this.code = status.value();
        this.status = status;
        this.message = message;
        this.path = path;
    }

}
