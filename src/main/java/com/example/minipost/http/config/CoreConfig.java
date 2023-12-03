package com.example.minipost.http.config;

import com.example.minipost.core.post.PostRepository;
import com.example.minipost.core.user.UserRepository;
import com.example.minipost.core.post.PostUseCase;
import com.example.minipost.core.user.UserUseCase;
import com.example.minipost.core.utils.JWTService;
import com.example.minipost.db.mysql.post.JpaPostRepository;
import com.example.minipost.db.mysql.post.PostRepositoryImpl;
import com.example.minipost.db.mysql.user.JpaUserRepository;
import com.example.minipost.db.mysql.user.UserRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.example.minipost.http.constant.SecurityConst.JWT_KEY;

@Configuration
public class CoreConfig {

    @Bean
    public JWTService jwtService() {
        return new JWTService(JWT_KEY, 1000L * 60 * 60 * 24 * 7);
    }

    @Bean
    public UserRepository userRepository(JpaUserRepository jpaUserRepository) {
        return new UserRepositoryImpl(jpaUserRepository);
    }

    @Bean
    public UserUseCase userUseCase(UserRepository userRepository, JWTService jwtService) {
        return new UserUseCase(userRepository, jwtService);
    }

    @Bean
    public PostRepository postRepository(JpaPostRepository jpaPostRepository) {
        return new PostRepositoryImpl(jpaPostRepository);
    }

    @Bean
    public PostUseCase postUseCase(PostRepository postrepository, UserRepository userRepository) {
        return new PostUseCase(postrepository, userRepository);
    }
}