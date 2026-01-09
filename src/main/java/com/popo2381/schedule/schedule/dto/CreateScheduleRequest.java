package com.popo2381.schedule.schedule.dto;

import lombok.Getter;
/**
 * 일정 생성 요청 DTO
 */
@Getter
public class CreateScheduleRequest {
    private Long userId;
    private String title;
    private String content;
}
