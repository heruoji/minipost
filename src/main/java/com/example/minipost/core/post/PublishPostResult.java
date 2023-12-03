package com.example.minipost.core.post;

import java.time.LocalDateTime;

public class PublishPostResult {
    public Long id;
    public Long authorId;
    public String username;
    public String content;
    public Integer likes;
    public LocalDateTime createdAt;
}
