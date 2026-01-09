package com.popo2381.schedule.dto;

import lombok.Getter;
/**
 * 일정 수정 요청 DTO
 */
@Getter
public class UpdateScheduleRequest {
    private String title;
    private String content;
}
