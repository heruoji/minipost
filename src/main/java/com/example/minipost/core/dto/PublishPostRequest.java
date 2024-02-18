package com.example.minipost.core.dto;

import java.time.LocalDateTime;

public class PublishPostRequest {
    public Long authorId;
    public String content;
    public LocalDateTime createdAt;
}
