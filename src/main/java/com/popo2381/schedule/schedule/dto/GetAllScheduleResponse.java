package com.popo2381.schedule.schedule.dto;

import lombok.Getter;

import java.time.LocalDateTime;
/**
 * 일정 전체 조회 응답 DTO
 */
@Getter
public class GetAllScheduleResponse {
    private final Long id;
    private final Long userId;
    private final String title;
    private final String content;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public GetAllScheduleResponse(Long id, Long userId, String title, String content, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
