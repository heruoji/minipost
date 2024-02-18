package com.example.minipost.core.dto;

import java.time.LocalDateTime;
import java.util.List;

public class PaginatedPostResult {
    public List<PaginatedPostItem> posts;

    public static class PaginatedPostItem {
        public Long id;
        public Long authorId;
        public String username;
        public String content;
        public Integer likes;
        public LocalDateTime createdAt;
    }
}
