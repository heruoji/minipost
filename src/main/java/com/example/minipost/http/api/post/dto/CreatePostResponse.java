package com.example.minipost.http.api.post.dto;

import java.time.LocalDateTime;

public class CreatePostResponse {
    public Long id;
    public Long authorId;
    public String username;
    public String content;
    public Integer likes;
    public LocalDateTime createdAt;

    public CreatePostResponse() {
    }
}
