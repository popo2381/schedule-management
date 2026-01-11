package com.popo2381.schedule.schedule.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
/**
 * 일정 생성 요청 DTO
 */
@Getter
public class CreateScheduleRequest {
    @NotBlank(message = "제목은 필수 입력 값입니다.")
    @Size(max = 30, message = "제목은 최대 30자 이내입니다.")
    private String title;
    @NotBlank(message = "내용은 필수 입력 값입니다.")
    @Size(max = 200, message = "내용은 최대 200자 이내입니다.")
    private String content;
}
