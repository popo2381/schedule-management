package com.popo2381.schedule.comment.controller;

import com.popo2381.schedule.auth.dto.SessionUser;
import com.popo2381.schedule.comment.dto.CreateCommentRequest;
import com.popo2381.schedule.comment.dto.CreateCommentResponse;
import com.popo2381.schedule.comment.dto.GetCommentResponse;
import com.popo2381.schedule.comment.service.CommentService;
import com.popo2381.schedule.common.exception.UnauthorizedException;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("schedules/{scheduleId}/comments")
    public ResponseEntity<CreateCommentResponse> create(
            @PathVariable Long scheduleId, @Valid @RequestBody CreateCommentRequest request, HttpSession session) {
        SessionUser loginUser = (SessionUser) session.getAttribute("LOGIN_USER");
        Long loginUserId = loginUser.getId();
        if (loginUserId == null) {
            throw new UnauthorizedException(loginUserId);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.save(scheduleId, request, loginUserId));
    }
    @GetMapping("schedules/{scheduleId}/comments")
    public ResponseEntity<List<GetCommentResponse>> findAll(@PathVariable Long scheduleId) {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.findBySchedule(scheduleId));
    }

}
