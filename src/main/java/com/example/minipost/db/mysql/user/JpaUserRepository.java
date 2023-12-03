package com.example.minipost.db.mysql.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaUserRepository extends JpaRepository<UserRecord, Long> {
    UserRecord findByEmail(String email);

    boolean existsByEmail(String email);
}
