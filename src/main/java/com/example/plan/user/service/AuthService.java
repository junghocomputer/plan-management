package com.example.plan.user.service;

import com.example.plan.common.exception.InvalidCredentialException;
import com.example.plan.user.dto.request.UserLoginRequestDto;
import com.example.plan.user.dto.response.UserLoginResponseDto;
import com.example.plan.user.entity.User;
import com.example.plan.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public UserLoginResponseDto login(UserLoginRequestDto dto) {
        User user = userRepository.findByEmail(dto.getEmail()).orElseThrow(
                () -> new InvalidCredentialException("해당 email은 존재하지 않습니다.")
        );

        String passWord = dto.getPassWord();
        if (passWord == null) {
            throw new InvalidCredentialException("비밀번호 먼저 입력하여 주시기 바랍니다.");
        }

        if (!passWord.equals(user.getPassWord())) {
            throw new InvalidCredentialException("비밀번호가 틀렸습니다");
        }

        return new UserLoginResponseDto(user.getId());
    }
}
