package com.example.minipost.http.api.post.dto;

import java.time.LocalDateTime;

public class GetPostsItem {
    public Long id;
    public Long authorId;
    public String username;
    public String content;
    public Integer likes;
    public LocalDateTime createdAt;

    public GetPostsItem() {
    }
}
