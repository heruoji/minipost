package com.example.minipost.core.usecase;

import com.example.minipost.core.dto.UserSignInRequestDto;
import com.example.minipost.core.dto.UserSignInResponseDto;
import com.example.minipost.core.entity.User;
import com.example.minipost.core.exception.AuthenticationException;
import com.example.minipost.core.repository.UserRepository;
import com.example.minipost.core.utils.JWTPrinciple;
import com.example.minipost.core.utils.JWTService;

public class SignInUseCase {
    private final UserRepository userRepository;
    private final JWTService jwtService;

    public SignInUseCase(UserRepository userRepository, JWTService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    public UserSignInResponseDto signIn(UserSignInRequestDto requestDto) {
        User user = userRepository.findByEmail(requestDto.email);
        if (user == null || !user.isPasswordMatched(requestDto.password)) {
            throw new AuthenticationException("Invalid username or password");
        }
        return buildUserSignInResponseDto(user);
    }

    private UserSignInResponseDto buildUserSignInResponseDto(User user) {
        UserSignInResponseDto userSignInResponseDto = new UserSignInResponseDto();
        userSignInResponseDto.token = getToken(user);
        return userSignInResponseDto;
    }

    private String getToken(User newUser) {
        JWTPrinciple principle = buildJWTPrinciple(newUser);
        return jwtService.createJWT(principle);
    }

    private static JWTPrinciple buildJWTPrinciple(User newUser) {
        JWTPrinciple principle = new JWTPrinciple();
        principle.issuer = "minipost";
        principle.id = newUser.getId();
        principle.username = newUser.getUsername();
        principle.role = newUser.getRole().toString();
        return principle;
    }
}
