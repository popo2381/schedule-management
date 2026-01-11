package com.popo2381.schedule.schedule.controller;

import com.popo2381.schedule.auth.dto.SessionUser;
import com.popo2381.schedule.common.exception.UnauthorizedException;
import com.popo2381.schedule.schedule.dto.*;
import com.popo2381.schedule.schedule.service.ScheduleService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping("/schedules")
    public ResponseEntity<CreateScheduleResponse> createSchedule(
            @Valid @RequestBody CreateScheduleRequest request, HttpSession session) {
        SessionUser loginUser = (SessionUser) session.getAttribute("LOGIN_USER"); // 로그인 사용자 가져오기
        Long loginUserId = loginUser.getId();
        if (loginUserId == null) {
            throw new UnauthorizedException(loginUserId);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleService.save(loginUserId, request));
    }

    @GetMapping("/schedules/{scheduleId}")
    public ResponseEntity<GetScheduleResponse> getSchedule(@PathVariable Long scheduleId) {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.findOne(scheduleId));
    }
    @GetMapping("schedules")
    public ResponseEntity<List<GetAllScheduleResponse>> getAllSchedules() {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.findAll());
    }
    @PutMapping("/schedules/{scheduleId}")
    public ResponseEntity<UpdateScheduleResponse> updateSchedule(
            @PathVariable Long scheduleId, @Valid @RequestBody UpdateScheduleRequest request, HttpSession session) {
        SessionUser loginUser = (SessionUser) session.getAttribute("LOGIN_USER");
        Long loginUserId = loginUser.getId();
        if (loginUserId == null) {
            throw new UnauthorizedException(loginUserId);
        }
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.update(scheduleId, loginUserId, request));
    }
    @DeleteMapping("schedules/{scheduleId}")
    public ResponseEntity<Void> deleteSchedule(
            @PathVariable Long scheduleId, @Valid @RequestBody DeleteScheduleRequest request, HttpSession session) {
        SessionUser loginUser = (SessionUser) session.getAttribute("LOGIN_USER");
        Long loginUserId = loginUser.getId();
        if (loginUserId == null) {
            throw new UnauthorizedException(loginUserId);
        }
        scheduleService.delete(scheduleId, loginUserId, request);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
