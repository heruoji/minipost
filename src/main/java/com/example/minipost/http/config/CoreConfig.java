package com.example.minipost.http.config;

import com.example.minipost.core.repository.PostRepository;
import com.example.minipost.core.repository.UserRepository;
import com.example.minipost.core.usecase.*;
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
    public SignUpUseCase signUpUseCase(UserRepository userRepository, JWTService jwtService) {
        return new SignUpUseCase(userRepository, jwtService);
    }

    @Bean
    public SignInUseCase signInUseCase(UserRepository userRepository, JWTService jwtService) {
        return new SignInUseCase(userRepository, jwtService);
    }

    @Bean
    public PostRepository postRepository(JpaPostRepository jpaPostRepository) {
        return new PostRepositoryImpl(jpaPostRepository);
    }

    @Bean
    public LikePostUseCase likePostUseCase(PostRepository postrepository) {
        return new LikePostUseCase(postrepository);
    }

    @Bean
    public PublishPostUseCase publishPostUseCase(PostRepository postrepository, UserRepository userRepository) {
        return new PublishPostUseCase(postrepository, userRepository);
    }

    @Bean
    public GetPaginatedPostUseCase getPaginatedPostUseCase(PostRepository postrepository) {
        return new GetPaginatedPostUseCase(postrepository);
    }
}