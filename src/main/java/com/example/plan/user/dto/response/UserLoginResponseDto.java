package com.example.plan.user.dto.response;

import lombok.Getter;

@Getter
public class UserLoginResponseDto {
    private final Long userId;

    public UserLoginResponseDto(Long userId) {
        this.userId = userId;
    }
}
