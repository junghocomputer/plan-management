package com.example.plan.schedule.dto.response;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleSaveResponseDto {
    private final Long id;
    private final String userName;
    private final String title;
    private final String content;
    private final LocalDateTime cratedAt;
    private final LocalDateTime updatedAt;

    public ScheduleSaveResponseDto(Long id, String userName, String title, String content, LocalDateTime cratedAt, LocalDateTime updatedAt) {
        this.id = id;
        this.userName = userName;
        this.title = title;
        this.content = content;
        this.cratedAt = cratedAt;
        this.updatedAt = updatedAt;
    }
}
