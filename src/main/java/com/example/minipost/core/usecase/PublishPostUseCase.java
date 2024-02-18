package com.example.minipost.core.usecase;

import com.example.minipost.core.dto.PublishPostRequest;
import com.example.minipost.core.dto.PublishPostResult;
import com.example.minipost.core.entity.Post;
import com.example.minipost.core.exception.InvalidRequestException;
import com.example.minipost.core.mapper.PostMapper;
import com.example.minipost.core.repository.PostRepository;
import com.example.minipost.core.entity.User;
import com.example.minipost.core.repository.UserRepository;

public class PublishPostUseCase {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PublishPostUseCase(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public PublishPostResult publishPost(PublishPostRequest request) {
        User author = userRepository.findById(request.authorId);
        if (author == null) {
            throw new InvalidRequestException("User with ID " + request.authorId + " not found");
        }
        Post post = Post.initializePost(author, request.content);
        Post savedPost = postRepository.save(post);
        return PostMapper.toPublishPostResponse(savedPost);
    }
}
