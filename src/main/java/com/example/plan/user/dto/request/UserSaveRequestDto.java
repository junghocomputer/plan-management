package com.example.plan.user.dto.request;

import lombok.Getter;

@Getter
public class UserSaveRequestDto {
    private String name;
    private String email;
    private String passWord;
}
