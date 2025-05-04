package com.example.plan.schedule.controller;

import com.example.plan.common.consts.Const;
import com.example.plan.schedule.dto.request.ScheduleSaveRequestDto;
import com.example.plan.schedule.dto.request.ScheduleUpdateRequestDto;
import com.example.plan.schedule.dto.response.ScheduleResponseDto;
import com.example.plan.schedule.dto.response.ScheduleSaveResponseDto;
import com.example.plan.schedule.dto.response.ScheduleUpdateResponseDto;
import com.example.plan.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor

public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping("/schedules")
    public ScheduleSaveResponseDto save(
            @SessionAttribute(name = Const.LOGIN_USER) Long userId,
            @RequestBody ScheduleSaveRequestDto dto
    ) {
        return scheduleService.save(userId, dto);
    }

    @GetMapping("/schedules")
    public List<ScheduleResponseDto> findAll() {
        return scheduleService.findAll();
    }

    @PutMapping("/schedules/{scheduleId}")
    public ScheduleUpdateResponseDto update(
            @SessionAttribute(name = Const.LOGIN_USER) Long userId,
            @PathVariable Long scheduleId,
            @RequestBody ScheduleUpdateRequestDto dto
            ) {
                return scheduleService.update(userId, scheduleId, dto);
    }

    @DeleteMapping("/schedules/{scheduleId}")
    public void delete(@PathVariable Long scheduleId) {
        scheduleService.deleteById(scheduleId);
    }
}
