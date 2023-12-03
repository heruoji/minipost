package com.example.minipost.http.api.post;

import com.example.minipost.core.post.PaginatedPostResult;
import com.example.minipost.core.post.PublishPostResult;
import com.example.minipost.http.config.CustomUserDetails;
import com.example.minipost.http.api.post.dto.CreatePostRequest;
import com.example.minipost.http.api.post.dto.CreatePostResponse;
import com.example.minipost.core.post.PostUseCase;
import com.example.minipost.core.post.PublishPostRequest;
import com.example.minipost.http.api.post.dto.GetPostsResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
public class PostController {
    private final PostUseCase postUseCase;

    public PostController(PostUseCase postUseCase) {
        this.postUseCase = postUseCase;
    }

    @PostMapping("/posts")
    public ResponseEntity<CreatePostResponse> createPost(@RequestBody CreatePostRequest request) {
        Long userId = getUserId();
        if (userId == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        PublishPostRequest useCaseRequest = PostMapper.toPublishPostRequest(request, userId);
        PublishPostResult useCaseResponse = postUseCase.publishPost(useCaseRequest);
        CreatePostResponse response = PostMapper.toCreatePostResponse(useCaseResponse);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/posts")
    public ResponseEntity<GetPostsResponse> getPosts(
            @RequestParam(defaultValue = "10") int limit,
            @RequestParam(defaultValue = "0") int offset
    ) {
        PaginatedPostResult userCaseResponse = postUseCase.getPaginatedPosts(limit, offset);
        GetPostsResponse response = PostMapper.toGetPostsResponse(userCaseResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/posts/{id}/like")
    public ResponseEntity<Void> likePost(@PathVariable Long id) {
        postUseCase.likePost(id);
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
