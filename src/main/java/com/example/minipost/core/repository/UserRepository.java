package com.example.minipost.core.repository;

import com.example.minipost.core.entity.User;

public interface UserRepository {

    User findByEmail(String email);
    User save(User user);

    User findById(Long authorId);

    boolean existsByEmail(String email);
}