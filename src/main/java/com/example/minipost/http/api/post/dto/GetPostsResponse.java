package com.example.minipost.http.api.post.dto;

import java.util.List;

public class GetPostsResponse {
    public List<GetPostsItem> posts;

    public GetPostsResponse(List<GetPostsItem> posts) {
        this.posts = posts;
    }
}
