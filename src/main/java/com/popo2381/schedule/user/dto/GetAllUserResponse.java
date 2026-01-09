package com.popo2381.schedule.user.dto;

import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 유저 전체조회 응답 DTO
 */
@Getter
public class GetAllUserResponse {
    private final Long id;
    private final String username;
    private final String email;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public GetAllUserResponse(Long id, String username, String email, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
