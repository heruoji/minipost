package com.example.minipost.db.mysql.user;

import com.example.minipost.core.entity.User;

public class UserMapper {
    public static UserRecord toRecord(User user) {
        UserRecord userRecord = new UserRecord();
        userRecord.username = user.getUsername();
        userRecord.email = user.getEmail();
        userRecord.password = user.getHashedPassword();
        userRecord.role = user.getRole().name();
        userRecord.createdAt = user.getCreatedAt();
        return userRecord;
    }

    public static User toEntity(UserRecord userRecord) {
        User user = new User();
        user.setId(userRecord.id);
        user.setUsername(userRecord.username);
        user.setEmail(userRecord.email);
        user.setHashedPassword(userRecord.password);
        user.setCreatedAt(userRecord.createdAt);
        user.setRole(toRole(userRecord.role));
        return user;
    }

    private static User.Role toRole(String role) {
        if (role == null)
            return null;
        return User.Role.valueOf(role);
    }
}
