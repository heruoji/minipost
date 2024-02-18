package com.example.minipost.core.usecase;

import com.example.minipost.core.exception.InvalidRequestException;
import com.example.minipost.core.entity.Post;
import com.example.minipost.core.repository.PostRepository;

public class LikePostUseCase {

    private final PostRepository postRepository;

    public LikePostUseCase(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void likePost(Long id) {
        Post post = postRepository.findById(id);
        if (post == null) {
            throw new InvalidRequestException("Post with ID " + id + " not found");
        }
        post.addLike();
        postRepository.save(post);
    }
}
