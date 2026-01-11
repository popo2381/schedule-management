package com.popo2381.schedule.auth.controller;

import com.popo2381.schedule.auth.dto.LoginRequest;
import com.popo2381.schedule.auth.dto.SessionUser;
import com.popo2381.schedule.auth.service.AuthService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<Void> login(@Valid @RequestBody LoginRequest request, HttpSession session) {
        SessionUser sessionUser = authService.login(request);
        session.setAttribute("LOGIN_USER", sessionUser);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(
            @SessionAttribute(name = "LOGIN_USER", required = false)
            SessionUser sessionUser, HttpSession session) {
        if(sessionUser == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        session.invalidate(); // 세션 무효화
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
