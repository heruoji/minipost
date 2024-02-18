package com.example.minipost.core.usecase;

import com.example.minipost.core.dto.UserSignUpRequestDto;
import com.example.minipost.core.dto.UserSignUpResponseDto;
import com.example.minipost.core.entity.User;
import com.example.minipost.core.exception.InvalidRequestException;
import com.example.minipost.core.repository.UserRepository;
import com.example.minipost.core.utils.JWTPrinciple;
import com.example.minipost.core.utils.JWTService;

public class SignUpUseCase {
    private final UserRepository userRepository;
    private final JWTService jwtService;

    public SignUpUseCase(UserRepository userRepository, JWTService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    public UserSignUpResponseDto signUp(UserSignUpRequestDto requestDto) {
        if (userRepository.existsByEmail(requestDto.email)) {
            throw new InvalidRequestException("Email already exists");
        }
        User newUser = User.initializeUser(requestDto.name, requestDto.email, requestDto.password);
        newUser = userRepository.save(newUser);
        return buildUserSignUpResponseDto(newUser);
    }

    private UserSignUpResponseDto buildUserSignUpResponseDto(User newUser) {
        UserSignUpResponseDto userSignUpResponseDto = new UserSignUpResponseDto();
        userSignUpResponseDto.token = getToken(newUser);
        return userSignUpResponseDto;
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
