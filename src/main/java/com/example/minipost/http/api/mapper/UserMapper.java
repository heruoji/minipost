package com.example.minipost.http.api.mapper;

import com.example.minipost.http.api.dto.SignInRequest;
import com.example.minipost.http.api.dto.SignInResponse;
import com.example.minipost.http.api.dto.SignUpRequest;
import com.example.minipost.http.api.dto.SignUpResponse;
import com.example.minipost.core.dto.UserSignInRequestDto;
import com.example.minipost.core.dto.UserSignInResponseDto;
import com.example.minipost.core.dto.UserSignUpRequestDto;
import com.example.minipost.core.dto.UserSignUpResponseDto;

public class UserMapper {

    public static UserSignUpRequestDto toUserSignUpDto(SignUpRequest request) {
        UserSignUpRequestDto dto = new UserSignUpRequestDto();
        dto.name = request.name;
        dto.email = request.email;
        dto.password = request.password;
        return dto;
    }

    public static SignUpResponse toRegisterUserResponse(UserSignUpResponseDto dto) {
        SignUpResponse response = new SignUpResponse();
        response.accessToken = dto.token;
        return response;
    }

    public static UserSignInRequestDto toUserSignInDto(SignInRequest request) {
        UserSignInRequestDto dto = new UserSignInRequestDto();
        dto.email = request.email;
        dto.password = request.password;
        return dto;
    }

    public static SignInResponse toSignInResponse(UserSignInResponseDto dto) {
        SignInResponse response = new SignInResponse();
        response.accessToken = dto.token;
        return response;
    }
}
