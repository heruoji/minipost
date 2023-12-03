package com.example.minipost.db.mysql.post;

import com.example.minipost.db.mysql.user.UserRecord;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "posts")
public class PostRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(nullable = false)
    public String content;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    public UserRecord author;

    @Column
    public Integer likes;

    @Column(nullable = false)
    public LocalDateTime createdAt;
}
