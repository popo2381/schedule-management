package com.popo2381.schedule.comment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CreateCommentRequest {
    @NotBlank(message = "내용은 필수 입력 값입니다.")
    @Size(max = 100, message = "내용은 최대 100자 이내입니다.")
    private String content;
}
