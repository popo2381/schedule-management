package com.popo2381.schedule.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
/**
 * 유저 삭제 요청 DTO
 */
@Getter
public class DeleteUserRequest {
    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    private String password;
}
