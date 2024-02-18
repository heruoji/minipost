package com.example.minipost.http.api.controller;

import com.example.minipost.core.dto.PaginatedPostResult;
import com.example.minipost.core.dto.PublishPostResult;
import com.example.minipost.core.usecase.*;
import com.example.minipost.http.api.mapper.PostMapper;
import com.example.minipost.http.config.CustomUserDetails;
import com.example.minipost.http.api.dto.CreatePostRequest;
import com.example.minipost.http.api.dto.CreatePostResponse;
import com.example.minipost.core.dto.PublishPostRequest;
import com.example.minipost.http.api.dto.GetPostsResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
public class PostController {

    private final PublishPostUseCase publishPostUseCase;
    private final GetPaginatedPostUseCase getPaginatedPostUseCase;
    private final LikePostUseCase likePostUseCase;

    public PostController(PublishPostUseCase publishPostUseCase, GetPaginatedPostUseCase getPaginatedPostUseCase, LikePostUseCase likePostUseCase) {
        this.publishPostUseCase = publishPostUseCase;
        this.getPaginatedPostUseCase = getPaginatedPostUseCase;
        this.likePostUseCase = likePostUseCase;
    }

    @PostMapping("/posts")
    public ResponseEntity<CreatePostResponse> createPost(@RequestBody CreatePostRequest request) {
        Long userId = getUserId();
        if (userId == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        PublishPostRequest useCaseRequest = PostMapper.toPublishPostRequest(request, userId);
        PublishPostResult useCaseResponse = publishPostUseCase.publishPost(useCaseRequest);
        CreatePostResponse response = PostMapper.toCreatePostResponse(useCaseResponse);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/posts")
    public ResponseEntity<GetPostsResponse> getPosts(
            @RequestParam(defaultValue = "10") int limit,
            @RequestParam(defaultValue = "0") int offset
    ) {
        PaginatedPostResult userCaseResponse = getPaginatedPostUseCase.getPaginatedPosts(limit, offset);
        GetPostsResponse response = PostMapper.toGetPostsResponse(userCaseResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/posts/{id}/like")
    public ResponseEntity<Void> likePost(@PathVariable Long id) {
        likePostUseCase.likePost(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    private Long getUserId() {
        return getUserDetails().getId();
    }

    private CustomUserDetails getUserDetails() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return (CustomUserDetails) auth.getPrincipal();
    }
}
