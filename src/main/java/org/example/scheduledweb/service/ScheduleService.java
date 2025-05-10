package org.example.scheduledweb.service;

import org.example.scheduledweb.dto.ScheduleRequestDto;
import org.example.scheduledweb.dto.ScheduleResponseDto;

public interface ScheduleService {

    ScheduleResponseDto saveSchedule(ScheduleRequestDto requestDto);

    ScheduleResponseDto findScheduleById(long id);
}
