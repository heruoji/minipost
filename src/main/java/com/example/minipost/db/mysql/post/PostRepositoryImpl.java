package com.example.minipost.db.mysql.post;

import com.example.minipost.core.entity.Post;
import com.example.minipost.core.repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class PostRepositoryImpl implements PostRepository {
    private final JpaPostRepository jpaPostRepository;

    public PostRepositoryImpl(JpaPostRepository jpaPostRepository) {
        this.jpaPostRepository = jpaPostRepository;
    }

    @Override
    public Post save(Post post) {
        PostRecord postRecord = PostMapper.toRecord(post);
        postRecord = jpaPostRepository.save(postRecord);
        return PostMapper.toEntity(postRecord);
    }

    @Override
    public List<Post> getPostsBy(int limit, int offset) {
        Pageable pageable = PageRequest.of(offset / limit, limit);
        Page<PostRecord> postRecords = jpaPostRepository.findAll(pageable);
        return postRecords.getContent().stream().map(PostMapper::toEntity).toList();
    }

    @Override
    public Post findById(Long id) {
        PostRecord postRecord = jpaPostRepository.findById(id).orElse(null);
        if (postRecord == null) {
            return null;
        }
        return PostMapper.toEntity(postRecord);
    }
}
