package com.popo2381.schedule.common.exception;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(Long loginUserId) {
        super("해당 로그인 유저를 찾을 수 없습니다. id=" + loginUserId);
    }

}
