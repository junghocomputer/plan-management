package com.example.plan.user.controller;

import com.example.plan.common.consts.Const;
import com.example.plan.user.dto.request.UserLoginRequestDto;
import com.example.plan.user.dto.response.UserLoginResponseDto;
import com.example.plan.user.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLoginRequestDto dto, HttpServletRequest request) {

        UserLoginResponseDto result = authService.login(dto);
        HttpSession session = request.getSession();
        session.setAttribute(Const.LOGIN_USER, result.getUserId());

        return ResponseEntity.ok("로그인에 성공 했습니다.");
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        //session 존재하면 로그인 된 경우
        if (session != null) {
            session.invalidate(); //해당 세션(데이터)를 삭제한다.
        }

        return ResponseEntity.ok("로그아웃에 성공 했습니다.");
    }
}
