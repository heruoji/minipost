package com.example.minipost.core.user;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Base64;

public class User {
    private Long id;
    private String username;
    private String email;
    private String hashedPassword;
    private Role role;

    private LocalDateTime createdAt;

    public enum Role {
        USER, ADMIN
    }

    public static User initializeUser(String username, String email, String rawPassword) {
        User user = new User();
        user.username = username;
        user.email = email;
        user.hashedPassword = hashPassword(rawPassword);
        user.createdAt = LocalDateTime.now();
        user.role = Role.USER;
        return user;
    }

    public boolean isPasswordMatched(String password) {
        return this.hashedPassword.equals(hashPassword(password));
    }

    private static String hashPassword(String password) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        byte[] hashedBytes = md.digest(password.getBytes());
        return Base64.getEncoder().encodeToString(hashedBytes);
    }

    public String getEmail() {
        return email;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public String getUsername() {
        return username;
    }

    public Long getId() {
        return id;
    }

    public Role getRole() {
        return role;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
