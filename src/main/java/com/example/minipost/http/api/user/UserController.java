package com.example.minipost.http.api.user;

import com.example.minipost.http.api.user.dto.SignInRequest;
import com.example.minipost.http.api.user.dto.SignInResponse;
import com.example.minipost.http.api.user.dto.SignUpRequest;
import com.example.minipost.http.api.user.dto.SignUpResponse;
import com.example.minipost.core.user.UserSignInResponseDto;
import com.example.minipost.core.user.UserSignUpResponseDto;
import com.example.minipost.core.user.UserUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserUseCase userUseCase;

    public UserController(UserUseCase userUseCase) {
        this.userUseCase = userUseCase;
    }

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponse> registerUser(@RequestBody SignUpRequest request) {
        UserSignUpResponseDto dto = userUseCase.signUp(UserMapper.toUserSignUpDto(request));
        SignUpResponse responseBody = UserMapper.toRegisterUserResponse(dto);
        return new ResponseEntity<>(responseBody, HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<SignInResponse> signIn(@RequestBody SignInRequest request) {
        UserSignInResponseDto dto = userUseCase.signIn(UserMapper.toUserSignInDto(request));
        SignInResponse responseBody = UserMapper.toSignInResponse(dto);
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }
}
