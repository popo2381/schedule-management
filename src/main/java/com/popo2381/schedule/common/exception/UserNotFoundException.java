package com.popo2381.schedule.common.exception;

/**
 * 유저가 존재하지 않을 때 발생하는 예외
 */
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(Long userId) {
        super("해당 유저를 찾을 수 없습니다. id=" + userId);
    }
}