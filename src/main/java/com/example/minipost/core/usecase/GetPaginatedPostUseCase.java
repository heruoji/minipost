package com.example.minipost.core.usecase;

import com.example.minipost.core.exception.InvalidRequestException;
import com.example.minipost.core.dto.PaginatedPostResult;
import com.example.minipost.core.entity.Post;
import com.example.minipost.core.mapper.PostMapper;
import com.example.minipost.core.repository.PostRepository;

import java.util.List;

public class GetPaginatedPostUseCase {

    private final PostRepository postRepository;

    public GetPaginatedPostUseCase(PostRepository postRepository) {
        this.postRepository = postRepository;
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
}
