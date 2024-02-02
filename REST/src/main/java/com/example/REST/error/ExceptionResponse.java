package com.example.REST.error;

import java.time.LocalDateTime;

public record ExceptionResponse(LocalDateTime timestamp,
        String message,
        String details) {

}