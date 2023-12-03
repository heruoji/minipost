package com.example.minipost.db.mysql.user;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class UserRecord {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    public Long id;

    @Column(nullable = false, unique = true)
    public String username;

    @Column(nullable = false, unique = true)
    public String email;

    public String password;

    @Column
    public String role;

    @Column
    public LocalDateTime createdAt;
}
