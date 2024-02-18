package com.example.minipost.core.entity;

import java.time.LocalDateTime;

public class Post {
    private Long id;
    private User author;
    private String content;
    private Integer likes;
    private LocalDateTime createdAt;

    public static Post initializePost(User author, String content) {
        Post post = new Post();
        post.author = author;
        post.content = content;
        post.createdAt = LocalDateTime.now();
        post.likes = 0;
        return post;
    }

    public void addLike() {
        if (likes == null)
            likes = 0;
        likes++;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public User getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }
}