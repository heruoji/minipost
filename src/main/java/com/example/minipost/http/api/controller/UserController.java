package com.example.minipost.http.api.controller;

import com.example.minipost.core.usecase.SignInUseCase;
import com.example.minipost.core.usecase.SignUpUseCase;
import com.example.minipost.http.api.mapper.UserMapper;
import com.example.minipost.http.api.dto.SignInRequest;
import com.example.minipost.http.api.dto.SignInResponse;
import com.example.minipost.http.api.dto.SignUpRequest;
import com.example.minipost.http.api.dto.SignUpResponse;
import com.example.minipost.core.dto.UserSignInResponseDto;
import com.example.minipost.core.dto.UserSignUpResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final SignUpUseCase signUpUseCase;

    private final SignInUseCase signInUseCase;

    public UserController(SignUpUseCase signUpUseCase, SignInUseCase signInUseCase) {
        this.signUpUseCase = signUpUseCase;
        this.signInUseCase = signInUseCase;
    }

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponse> registerUser(@RequestBody SignUpRequest request) {
        UserSignUpResponseDto dto = signUpUseCase.signUp(UserMapper.toUserSignUpDto(request));
        SignUpResponse responseBody = UserMapper.toRegisterUserResponse(dto);
        return new ResponseEntity<>(responseBody, HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<SignInResponse> signIn(@RequestBody SignInRequest request) {
        UserSignInResponseDto dto = signInUseCase.signIn(UserMapper.toUserSignInDto(request));
        SignInResponse responseBody = UserMapper.toSignInResponse(dto);
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }
}
