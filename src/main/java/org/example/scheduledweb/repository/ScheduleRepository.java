package org.example.scheduledweb.repository;

import org.example.scheduledweb.dto.ScheduleResponseDto;
import org.example.scheduledweb.entity.Schedule;

public interface ScheduleRepository {

    ScheduleResponseDto saveSchedule(Schedule schedule);

    Schedule findScheduleByIdElseThrow(long id);
}
