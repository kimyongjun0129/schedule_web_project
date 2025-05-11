package org.example.scheduledweb.service;

import org.example.scheduledweb.dto.ScheduleRequestDto;
import org.example.scheduledweb.dto.ScheduleResponseDto;

import java.util.List;

public interface ScheduleService {

    ScheduleResponseDto saveSchedule(ScheduleRequestDto requestDto);

    List<ScheduleResponseDto> findAllSchedules();

    ScheduleResponseDto findScheduleById(long id);

    ScheduleResponseDto updateUserNameOrToDoContent(long id, long password, String userName, String toDoContent);

    void deleteSchedule(long id, long password);
}
