package com.popo2381.schedule.user.dto;

import lombok.Getter;
/**
 * 유저 수정 요청 DTO
 */
@Getter
public class UpdateUserRequest {
    private String username;
    private String email;
}
