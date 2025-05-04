package com.example.plan.schedule.service;

import com.example.plan.schedule.dto.request.ScheduleSaveRequestDto;
import com.example.plan.schedule.dto.request.ScheduleUpdateRequestDto;
import com.example.plan.schedule.dto.response.ScheduleResponseDto;
import com.example.plan.schedule.dto.response.ScheduleSaveResponseDto;
import com.example.plan.schedule.dto.response.ScheduleUpdateResponseDto;
import com.example.plan.schedule.entity.Schedule;
import com.example.plan.schedule.repository.ScheduleRepository;
import com.example.plan.user.entity.User;
import com.example.plan.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    @Transactional
    public ScheduleSaveResponseDto save(Long userId, ScheduleSaveRequestDto dto) {

        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalStateException("해당 유저 id를 조회할 수 없습니다.")
        );

        Schedule schedule = new Schedule(
                user,
                dto.getTitle(),
                dto.getContent()
        );

        Schedule savedSchedule = scheduleRepository.save(schedule);

        return new ScheduleSaveResponseDto(
                savedSchedule.getId(),
                user.getName(),
                savedSchedule.getTitle(),
                savedSchedule.getContent(),
                savedSchedule.getCratedAt(),
                savedSchedule.getUpdatedAt()
        );
    }

    @Transactional(readOnly = true)
    public List<ScheduleResponseDto> findAll() {
        List<Schedule> schedules = scheduleRepository.findAll();

        List<ScheduleResponseDto> dtos = new ArrayList<>();

        for (Schedule schedule : schedules) {
            dtos.add(new ScheduleResponseDto(
                    schedule.getId(),
                    schedule.getTitle(),
                    schedule.getContent(),
                    schedule.getCratedAt(),
                    schedule.getUpdatedAt()
            ));
        }
        return dtos;
    }

    @Transactional
    public ScheduleUpdateResponseDto update(Long userId, Long scheduleId, ScheduleUpdateRequestDto dto) {

        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalStateException("해당 유저 id를 조회할 수 없습니다.")
        );

        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("해당 스케줄을 조회할 수 없습니다.")
        );

        if (!user.getId().equals(schedule.getUser().getId())) {
            throw new IllegalStateException("작성자와 일치하지 않으므로 권한이 없습니다.");
        }

        schedule.update(dto.getTitle(), dto.getContent());

        return new ScheduleUpdateResponseDto(
                schedule.getId(),
                user.getName(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getCratedAt(),
                schedule.getUpdatedAt()
        );

    }

    @Transactional
    public void deleteById(Long scheduleId) {
        if (!scheduleRepository.existsById(scheduleId)) {
            throw new IllegalStateException("해당 id는 존재하지 않습니다.");
        }
        scheduleRepository.deleteById(scheduleId);
    }
}
