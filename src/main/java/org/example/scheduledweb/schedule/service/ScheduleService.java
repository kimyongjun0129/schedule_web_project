package org.example.scheduledweb.schedule.service;

import org.example.scheduledweb.schedule.dto.ScheduleRequestDto;
import org.example.scheduledweb.schedule.dto.ScheduleResponseDto;

import java.util.List;

public interface ScheduleService {

    ScheduleResponseDto saveSchedule(ScheduleRequestDto requestDto);

    List<ScheduleResponseDto> findAllSchedules();

    ScheduleResponseDto findScheduleById(long id);

    ScheduleResponseDto updateToDoContent(long id, long password, String toDoContent);

    void deleteSchedule(long id, long password);
}
