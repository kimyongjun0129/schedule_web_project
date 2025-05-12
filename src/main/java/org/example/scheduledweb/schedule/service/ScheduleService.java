package org.example.scheduledweb.schedule.service;

import org.example.scheduledweb.schedule.dto.ScheduleRequestDto;
import org.example.scheduledweb.schedule.dto.ScheduleResponseDto;
import org.example.scheduledweb.schedule.entity.Paging;

import java.util.List;

public interface ScheduleService {

    ScheduleResponseDto saveSchedule(ScheduleRequestDto requestDto);

    List<ScheduleResponseDto> findAllSchedules();

    ScheduleResponseDto findScheduleById(long id);

    void updateToDoContent(long id, long userId, String toDoContent);

    void deleteSchedule(long id, long password);

    List<ScheduleResponseDto> pagination (Paging paging);

}
