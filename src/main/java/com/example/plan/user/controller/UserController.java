package com.example.plan.user.controller;

import com.example.plan.user.dto.request.UserSaveRequestDto;
import com.example.plan.user.dto.request.UserUpateRequestDto;
import com.example.plan.user.dto.response.UserResponseDto;
import com.example.plan.user.dto.response.UserSaveResponseDto;
import com.example.plan.user.dto.response.UserUpadteResponseDto;
import com.example.plan.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/users/signup")
    public UserSaveResponseDto signup(@RequestBody UserSaveRequestDto dto) {
        return userService.save(dto);
    }

    @GetMapping("/users")
    public List<UserResponseDto> findAll() {
        return userService.findAll();
    }

    @PutMapping("/users/{userId}")
    public UserUpadteResponseDto update(@PathVariable Long userId, @RequestBody UserUpateRequestDto dto) {
        return userService.update(userId, dto);
    }

    @DeleteMapping("/users/{userId}")
    public void delete(@PathVariable Long userId) {
        userService.deleteById(userId);
    }
}
