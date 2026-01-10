package com.popo2381.schedule.user.dto;

import lombok.Getter;
/**
 * 유저 생성 요청 DTO
 */
@Getter
public class CreateUserRequest {
    private String username;
    private String email;
    private String password;
}
