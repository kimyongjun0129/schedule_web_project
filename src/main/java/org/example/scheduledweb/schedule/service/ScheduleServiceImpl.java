package org.example.scheduledweb.schedule.service;

import org.example.scheduledweb.schedule.dto.ScheduleRequestDto;
import org.example.scheduledweb.schedule.dto.ScheduleResponseDto;
import org.example.scheduledweb.schedule.entity.Paging;
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
        // 등록된 user인지 userId로 검증
        Integer userCount = scheduleRepository.findUserByUserId(requestDto.getUserId());

        // userCount가 0이면 비등록 유저, 1이면 등록 유저
        if (userCount == null || userCount == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This user doesn't exist.");
        }

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
        Schedule schedule = scheduleRepository.findScheduleById(id);

        if (schedule == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This schedule Does not exists (schedule id : " + id + ")");
        }

        return new ScheduleResponseDto(schedule);
    }

    @Override
    public void updateToDoContent(long id, Long userId, String toDoContent) {
        // 필수값 검증
        if (toDoContent == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The userName and toDoContent are required values.");
        }

        // 유효한 사용자인지 검증
        Schedule schedule = scheduleRepository.findScheduleByUserId(userId);

        if (schedule == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This user doesn't exist.");
        }

        if (userId != schedule.getUserId()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The userId is not the same as the userId for this schedule");
        }

        // 식별자의 schedule 없다면?
        int scheduleCount = scheduleRepository.updateToDoContent(id, toDoContent);

        if (scheduleCount == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This schedule Does not exists (schedule id : " + id + ")");
        }
    }

    @Override
    public void deleteSchedule(long id, long userId) {
        // 사용자 ID 검증
        if (userId != scheduleRepository.findScheduleByUserId(id).getUserId()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The password is not the same as the password for this schedule");
        }

        int deletedRow = scheduleRepository.deleteSchedule(id);

        // NPE 방지
        if(deletedRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }
    }

    @Override
    public List<ScheduleResponseDto> pagination(Paging paging) {
        return scheduleRepository.pagination(paging);
    }
}

