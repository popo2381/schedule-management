package com.popo2381.schedule.comment.service;

import com.popo2381.schedule.comment.dto.CreateCommentRequest;
import com.popo2381.schedule.comment.dto.CreateCommentResponse;
import com.popo2381.schedule.comment.dto.GetCommentResponse;
import com.popo2381.schedule.comment.entity.Comment;
import com.popo2381.schedule.comment.repository.CommentRepository;
import com.popo2381.schedule.common.exception.CommentLimitExceededException;
import com.popo2381.schedule.common.exception.ScheduleNotFoundException;
import com.popo2381.schedule.common.exception.UserNotFoundException;
import com.popo2381.schedule.schedule.entity.Schedule;
import com.popo2381.schedule.schedule.repository.ScheduleRepository;
import com.popo2381.schedule.user.entity.User;
import com.popo2381.schedule.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    @Transactional
    public CreateCommentResponse save(Long scheduleId, CreateCommentRequest request, Long loginUserId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new ScheduleNotFoundException(scheduleId)
        );
        User user = userRepository.findById(loginUserId).orElseThrow(
                () -> new UserNotFoundException(loginUserId)
        );
        if(commentRepository.countByScheduleId(scheduleId) >= 10) {
            throw new CommentLimitExceededException();
        }
        Comment comment = new Comment(schedule, user, request.getContent());
        Comment savedComment = commentRepository.save(comment);
        return new CreateCommentResponse(
                savedComment.getId(),
                loginUserId,
                savedComment.getContent(),
                savedComment.getCreatedAt(),
                savedComment.getModifiedAt()
        );
    }

    @Transactional(readOnly = true)
    public List<GetCommentResponse> findBySchedule(Long scheduleId) {
        scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new ScheduleNotFoundException(scheduleId)
        );
        return commentRepository.findAllByScheduleId(scheduleId).stream().
                map(comment -> new GetCommentResponse(
                        comment.getId(),
                        comment.getUser().getId(),
                        comment.getContent(),
                        comment.getCreatedAt(),
                        comment.getModifiedAt()
                )).toList();
    }
}
