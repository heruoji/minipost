package com.example.minipost.http.api.mapper;

import com.example.minipost.core.dto.PaginatedPostResult;
import com.example.minipost.core.dto.PublishPostResult;
import com.example.minipost.http.api.dto.CreatePostRequest;
import com.example.minipost.core.dto.PublishPostRequest;
import com.example.minipost.http.api.dto.CreatePostResponse;
import com.example.minipost.http.api.dto.GetPostsItem;
import com.example.minipost.http.api.dto.GetPostsResponse;

import java.util.List;
import java.util.stream.Collectors;

public class PostMapper {

    public static PublishPostRequest toPublishPostRequest(CreatePostRequest request, Long authorId) {
        PublishPostRequest publishPostRequest = new PublishPostRequest();
        publishPostRequest.authorId = authorId;
        publishPostRequest.content = request.content;
        return publishPostRequest;
    }

    public static CreatePostResponse toCreatePostResponse(PublishPostResult publishPostResponse) {
        CreatePostResponse response = new CreatePostResponse();
        response.id = publishPostResponse.id;
        response.authorId = publishPostResponse.authorId;
        response.username = publishPostResponse.username;
        response.content = publishPostResponse.content;
        response.likes = publishPostResponse.likes;
        response.createdAt = publishPostResponse.createdAt;
        return response;
    }

    public static GetPostsResponse toGetPostsResponse(PaginatedPostResult paginatedPostResult) {
        List<GetPostsItem> posts = paginatedPostResult.posts.stream()
                .map(PostMapper::convertToGetPostsItem)
                .collect(Collectors.toList());
        return new GetPostsResponse(posts);
    }

    private static GetPostsItem convertToGetPostsItem(PaginatedPostResult.PaginatedPostItem paginatedPostItem) {
        GetPostsItem getPostsItem = new GetPostsItem();
        getPostsItem.id = paginatedPostItem.id;
        getPostsItem.authorId = paginatedPostItem.authorId;
        getPostsItem.username = paginatedPostItem.username;
        getPostsItem.content = paginatedPostItem.content;
        getPostsItem.likes = paginatedPostItem.likes;
        getPostsItem.createdAt = paginatedPostItem.createdAt;
        return getPostsItem;
    }

}
