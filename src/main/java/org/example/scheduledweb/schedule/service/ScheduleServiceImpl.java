package org.example.scheduledweb.schedule.service;

import org.example.scheduledweb.schedule.dto.ScheduleRequestDto;
import org.example.scheduledweb.schedule.dto.ScheduleResponseDto;
import org.example.scheduledweb.schedule.entity.Schedule;
import org.example.scheduledweb.schedule.repository.ScheduleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService{

    private final ScheduleRepository scheduleRepository;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public ScheduleResponseDto saveSchedule(ScheduleRequestDto requestDto) {
        Schedule schedule = new Schedule(requestDto.getUserId(), requestDto.getToDoContent());
        return scheduleRepository.saveSchedule(schedule);
    }

    @Override
    public List<ScheduleResponseDto> findAllSchedules() {
        // 전체 조회
        return scheduleRepository.findAllSchedules();
    }

    @Override
    public ScheduleResponseDto findScheduleById(long id) {
        Schedule schedule = scheduleRepository.findScheduleByIdElseThrow(id);
        return new ScheduleResponseDto(schedule);
    }

    @Override
    public ScheduleResponseDto updateToDoContent(long id, long userId, String toDoContent) {
        // 필수값 검증
        if (toDoContent == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The userName and toDoContent are required values.");
        }

        // 비밀번호 검증
        if (userId != scheduleRepository.findScheduleUserIdByIdElseThrow(id).getUserId()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The password is not the same as the password for this schedule");
        }

        int updatedRow = scheduleRepository.updateUserNameOrToDoContent(id, toDoContent);

        // NPE 방지
        if (updatedRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }

        // 식별자의 schedule 없다면?
        Schedule schedule = scheduleRepository.findScheduleByIdElseThrow(id);

        return new ScheduleResponseDto(schedule);
    }

    @Override
    public void deleteSchedule(long id, long userId) {
        // 비밀번호 검증
        if (userId != scheduleRepository.findScheduleUserIdByIdElseThrow(id).getUserId()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The password is not the same as the password for this schedule");
        }

        int deletedRow = scheduleRepository.deleteSchedule(id);

        // NPE 방지
        if(deletedRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }
    }
}

