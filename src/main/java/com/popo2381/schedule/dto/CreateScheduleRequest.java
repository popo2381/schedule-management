package com.popo2381.schedule.dto;

import lombok.Getter;
/**
 * 일정 생성 요청 DTO
 */
@Getter
public class CreateScheduleRequest {
    private String writer;
    private String title;
    private String content;
}
