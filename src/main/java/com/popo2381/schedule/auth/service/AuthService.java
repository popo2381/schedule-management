package com.popo2381.schedule.auth.service;

import com.popo2381.schedule.auth.dto.LoginRequest;
import com.popo2381.schedule.auth.dto.SessionUser;
import com.popo2381.schedule.common.exception.EmailNotFoundException;
import com.popo2381.schedule.common.exception.InvalidPasswordException;
import com.popo2381.schedule.config.PasswordEncoder;
import com.popo2381.schedule.user.entity.User;
import com.popo2381.schedule.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public SessionUser login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(
                () -> new EmailNotFoundException(request.getEmail())
        );
        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new InvalidPasswordException();
        }

        return new SessionUser(user.getId(), user.getUsername(), user.getEmail());
    }
}