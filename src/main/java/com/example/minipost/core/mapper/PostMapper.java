package com.example.minipost.core.mapper;

import com.example.minipost.core.entity.Post;
import com.example.minipost.core.dto.PaginatedPostResult;
import com.example.minipost.core.dto.PublishPostResult;

import java.util.ArrayList;
import java.util.List;

public class PostMapper {

    public static PublishPostResult toPublishPostResponse(Post post) {
        PublishPostResult response = new PublishPostResult();
        response.id = post.getId();
        response.authorId = post.getAuthor().getId();
        response.username = post.getAuthor().getUsername();
        response.content = post.getContent();
        response.likes = post.getLikes();
        response.createdAt = post.getCreatedAt();
        return response;
    }

    public static PaginatedPostResult toPaginatedPostResult(List<Post> posts) {
        PaginatedPostResult result = new PaginatedPostResult();
        List<PaginatedPostResult.PaginatedPostItem> items = new ArrayList<>();
        posts.stream().map(post -> {
            PaginatedPostResult.PaginatedPostItem item = new PaginatedPostResult.PaginatedPostItem();
            item.id = post.getId();
            item.authorId = post.getAuthor().getId();
            item.username = post.getAuthor().getUsername();
            item.content = post.getContent();
            item.likes = post.getLikes();
            item.createdAt = post.getCreatedAt();
            return item;
        }).forEach(items::add);
        result.posts = items;
        return result;
    }
}
