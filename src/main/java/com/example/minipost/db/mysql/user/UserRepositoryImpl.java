package com.example.minipost.db.mysql.user;

import com.example.minipost.core.user.User;
import com.example.minipost.core.user.UserRepository;

public class UserRepositoryImpl implements UserRepository {
    private final JpaUserRepository jpaUserRepository;

    public UserRepositoryImpl(JpaUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
    }

    @Override
    public User findByEmail(String email) {
        UserRecord userRecord = jpaUserRepository.findByEmail(email);
        if (userRecord == null)
            return null;
        return UserMapper.toEntity(userRecord);
    }

    @Override
    public User save(User user) {
        UserRecord userRecord = UserMapper.toRecord(user);
        userRecord = jpaUserRepository.save(userRecord);
        return UserMapper.toEntity(userRecord);
    }

    @Override
    public User findById(Long authorId) {
        UserRecord userRecord = jpaUserRepository.findById(authorId).orElse(null);
        if (userRecord == null)
            return null;
        return UserMapper.toEntity(userRecord);
    }

    @Override
    public boolean existsByEmail(String email) {
        return jpaUserRepository.existsByEmail(email);
    }
}
