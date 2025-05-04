package com.example.plan.user.service;

import com.example.plan.user.dto.request.UserSaveRequestDto;
import com.example.plan.user.dto.request.UserUpateRequestDto;
import com.example.plan.user.dto.response.UserResponseDto;
import com.example.plan.user.dto.response.UserSaveResponseDto;
import com.example.plan.user.dto.response.UserUpadteResponseDto;
import com.example.plan.user.entity.User;
import com.example.plan.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;


    public UserSaveResponseDto save(UserSaveRequestDto dto) {

        User user = new User(dto.getName(), dto.getEmail(), dto.getPassWord());

        User savedUser = userRepository.save(user);
        return new UserSaveResponseDto(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getEmail(),
                savedUser.getCratedAt(),
                savedUser.getUpdatedAt()
        );
    }

    @Transactional(readOnly = true)
    public List<UserResponseDto> findAll() {
        List<User> users = userRepository.findAll();

        return users.stream()
                .map(user -> new UserResponseDto( //map 통과한 순간 UserResponseDto로 치환시켜주기
                        user.getId(),
                        user.getName(),
                        user.getEmail(),
                        user.getCratedAt(),
                        user.getUpdatedAt()
                )).toList(); // 치환해준 것들을 다시 모아주기
    }

    public UserUpadteResponseDto update(Long userId, UserUpateRequestDto dto) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalStateException("해당 유저 id는 존재하지 않습니다.")
        );

        user.update(dto.getName(), dto.getEmail(), dto.getPassWord());

        return new UserUpadteResponseDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getCratedAt(),
                user.getUpdatedAt()
        );
    }

    public void deleteById(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new IllegalStateException("해당 id는 조회되지 않습니다.");
        }
        userRepository.deleteById(userId);
    }
}
