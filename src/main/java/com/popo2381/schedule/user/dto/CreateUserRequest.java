package com.popo2381.schedule.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
/**
 * 유저 생성 요청 DTO
 */
@Getter
public class CreateUserRequest {
    @NotBlank(message = "유저 이름은 필수 입력 값입니다.")
    @Size(max = 10, message = "유저 이름은 최대 10자 이내입니다.")
    private String username;
    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "이메일 형식이어야 합니다.")
    private String email;
    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
//    @Size(min = 8, max = 30, message = "비밀번호는 최소 8자, 최대 30자 이내입니다.")
    private String password;
}
