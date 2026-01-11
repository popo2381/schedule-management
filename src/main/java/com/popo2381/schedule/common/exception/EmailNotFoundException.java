package com.popo2381.schedule.common.exception;

/**
 * 이메일이 존재하지 않을 때 발생하는 예외
 */
public class EmailNotFoundException extends RuntimeException {

    public EmailNotFoundException(String email) {
        super("해당 이메일을 찾을 수 없습니다. email=" + email);
    }
}