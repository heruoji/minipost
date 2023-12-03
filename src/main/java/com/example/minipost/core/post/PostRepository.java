package com.example.minipost.core.post;

import java.util.List;

public interface PostRepository {

    Post save(Post post);

    List<Post> getPostsBy(int limit, int offset);

    Post findById(Long id);
}
