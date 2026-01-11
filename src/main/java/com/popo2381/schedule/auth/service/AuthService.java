package com.popo2381.schedule.auth.service;

import com.popo2381.schedule.auth.dto.LoginRequest;
import com.popo2381.schedule.auth.dto.SessionUser;
import com.popo2381.schedule.user.entity.User;
import com.popo2381.schedule.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public SessionUser login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(
                () -> new IllegalArgumentException("Invalid email address provided")
        );
        if(!user.getPassword().equals(request.getPassword())) {
            throw new IllegalArgumentException("Invalid password provided");
        }

        return new SessionUser(user.getId(), user.getUsername(), user.getEmail());
    }
}