package com.example.minipost.db.mysql.post;

import com.example.minipost.core.post.Post;
import com.example.minipost.core.user.User;
import com.example.minipost.db.mysql.user.UserMapper;
import com.example.minipost.db.mysql.user.UserRecord;

public class PostMapper {

    public static PostRecord toRecord(Post post) {
        PostRecord postRecord = new PostRecord();
        if (post.getId() != null) {
            postRecord.id = post.getId();
        }
        postRecord.content = post.getContent();
        postRecord.author = toRecord(post.getAuthor());
        postRecord.likes = post.getLikes();
        postRecord.createdAt = post.getCreatedAt();
        return postRecord;
    }

    private static UserRecord toRecord(User user) {
        UserRecord userRecord = new UserRecord();
        userRecord.id = user.getId();
        userRecord.username = user.getUsername();
        userRecord.email = user.getEmail();
        userRecord.password = user.getHashedPassword();
        userRecord.role = user.getRole().name();
        userRecord.createdAt = user.getCreatedAt();
        return userRecord;
    }

    public static Post toEntity(PostRecord postRecord) {
        UserRecord userRecord = postRecord.author;
        User user = UserMapper.toEntity(userRecord);
        Post post = new Post();
        post.setId(postRecord.id);
        post.setAuthor(user);
        post.setContent(postRecord.content);
        post.setLikes(postRecord.likes);
        post.setCreatedAt(postRecord.createdAt);
        return post;
    }
}
