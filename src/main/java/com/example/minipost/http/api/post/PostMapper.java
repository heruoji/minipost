package com.example.minipost.http.api.post;

import com.example.minipost.core.post.PaginatedPostResult;
import com.example.minipost.core.post.PublishPostResult;
import com.example.minipost.http.api.post.dto.CreatePostRequest;
import com.example.minipost.core.post.PublishPostRequest;
import com.example.minipost.http.api.post.dto.CreatePostResponse;
import com.example.minipost.http.api.post.dto.GetPostsItem;
import com.example.minipost.http.api.post.dto.GetPostsResponse;

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
