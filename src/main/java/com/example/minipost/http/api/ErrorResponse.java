package com.example.minipost.http.api;

import java.time.LocalDateTime;

public class ErrorResponse {
    public LocalDateTime timestamp;
    public String error;
    public String message;
    public String path;
}
