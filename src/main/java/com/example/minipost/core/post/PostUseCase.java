package com.example.minipost.core.post;

import com.example.minipost.core.exception.InvalidRequestException;
import com.example.minipost.core.user.User;
import com.example.minipost.core.user.UserRepository;

import java.util.List;

public class PostUseCase {

    private PostRepository postRepository;
    private UserRepository userRepository;

    public PostUseCase(PostRepository postRepository, UserRepository userRepository) {
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

    public PaginatedPostResult getPaginatedPosts(int limit, int offset) {
        if (!isLimitValid(limit)) {
            throw new InvalidRequestException("Invalid limit");
        }
        if (!isOffsetValid(offset)) {
            throw new InvalidRequestException("Invalid offset");
        }
        List<Post> posts = postRepository.getPostsBy(limit, offset);
        return PostMapper.toPaginatedPostResult(posts);
    }

    private boolean isLimitValid(int limit) {
        return limit >= 1 && limit <= 100;
    }

    private boolean isOffsetValid(int offset) {
        return offset >= 0;
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
