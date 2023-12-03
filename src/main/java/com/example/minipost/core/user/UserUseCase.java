package com.example.minipost.core.user;

import com.example.minipost.core.exception.AuthenticationException;
import com.example.minipost.core.exception.InvalidRequestException;
import com.example.minipost.core.utils.JWTPrinciple;
import com.example.minipost.core.utils.JWTService;

public class UserUseCase {
    private final UserRepository userRepository;
    private final JWTService jwtService;

    public UserUseCase(UserRepository userRepository, JWTService jwtService) {
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
