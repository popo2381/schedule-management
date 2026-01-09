package com.popo2381.schedule.dto;

import lombok.Getter;

import java.time.LocalDateTime;
/**
 * 일정 생성 응답 DTO
 */
@Getter
public class CreateScheduleResponse {
    private final Long id;
    private final String writer;
    private final String title;
    private final String content;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public CreateScheduleResponse(Long id, String writer, String title, String content, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
