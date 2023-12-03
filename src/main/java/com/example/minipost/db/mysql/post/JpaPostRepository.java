package com.example.minipost.db.mysql.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaPostRepository extends JpaRepository<PostRecord, Long> {

}
