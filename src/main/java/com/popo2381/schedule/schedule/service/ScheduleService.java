package com.popo2381.schedule.schedule.service;

import com.popo2381.schedule.common.exception.InvalidPasswordException;
import com.popo2381.schedule.common.exception.UserNotFoundException;
import com.popo2381.schedule.config.PasswordEncoder;
import com.popo2381.schedule.schedule.entity.Schedule;
import com.popo2381.schedule.common.exception.ScheduleNotFoundException;
import com.popo2381.schedule.schedule.repository.ScheduleRepository;
import com.popo2381.schedule.schedule.dto.*;
import com.popo2381.schedule.user.entity.User;
import com.popo2381.schedule.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public CreateScheduleResponse save(Long loginUserId, CreateScheduleRequest request) {
        User user = userRepository.findById(loginUserId).orElseThrow(
                () -> new UserNotFoundException(loginUserId)
        );
        Schedule schedule = new Schedule(
                user,
                request.getTitle(),
                request.getContent()
        );
        Schedule savedSchedule = scheduleRepository.save(schedule);
        return new CreateScheduleResponse(
                savedSchedule.getId(),
                savedSchedule.getUser().getId(),
                savedSchedule.getTitle(),
                savedSchedule.getContent(),
                savedSchedule.getCreatedAt(),
                savedSchedule.getModifiedAt()
        );
    }

    @Transactional(readOnly = true)
    public GetScheduleResponse findOne(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new ScheduleNotFoundException(scheduleId)
        );
        return new GetScheduleResponse(
                schedule.getId(),
                schedule.getUser().getId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }

    @Transactional(readOnly = true)
    public List<GetAllScheduleResponse> findAll() {
        List<Schedule> schedules = scheduleRepository.findAll();
        return schedules.stream().map(schedule -> new GetAllScheduleResponse(
                schedule.getId(),
                schedule.getUser().getId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        )).toList();
    }

    @Transactional
    public UpdateScheduleResponse update(Long scheduleId, Long loginUserId, UpdateScheduleRequest request) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new ScheduleNotFoundException(scheduleId)
        );
        User user = userRepository.findById(loginUserId).orElseThrow(
                () -> new UserNotFoundException(loginUserId)
        );
        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new InvalidPasswordException();
        }
        schedule.update(request.getTitle(), request.getContent());
        return new UpdateScheduleResponse(
                schedule.getId(),
                schedule.getUser().getId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }

    @Transactional
    public Void delete(Long scheduleId, Long loginUserId, DeleteScheduleRequest request) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new ScheduleNotFoundException(scheduleId)
        );
        User user = userRepository.findById(loginUserId).orElseThrow(
                () -> new UserNotFoundException(loginUserId)
        );
        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new InvalidPasswordException();
        }
        scheduleRepository.deleteById(scheduleId);
        return null;
    }
}
