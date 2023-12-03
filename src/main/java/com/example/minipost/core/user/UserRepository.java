package com.example.minipost.core.user;

public interface UserRepository {

    User findByEmail(String email);
    User save(User user);

    User findById(Long authorId);

    boolean existsByEmail(String email);
}