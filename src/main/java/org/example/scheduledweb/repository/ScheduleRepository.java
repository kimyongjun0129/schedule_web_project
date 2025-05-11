package org.example.scheduledweb.repository;

import org.example.scheduledweb.dto.ScheduleResponseDto;
import org.example.scheduledweb.entity.Schedule;

import java.util.List;

public interface ScheduleRepository {

    ScheduleResponseDto saveSchedule(Schedule schedule);

    List<ScheduleResponseDto> findAllSchedules();

    Schedule findScheduleByIdElseThrow(long id);

    Schedule findSchedulePasswordByIdElseThrow(long id);

    int updateUserNameOrToDoContent(long id, String userName, String toDoContent);

    int deleteSchedule (long id);
}
