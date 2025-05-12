package org.example.scheduledweb.schedule.repository;

import org.example.scheduledweb.schedule.dto.ScheduleResponseDto;
import org.example.scheduledweb.schedule.entity.Paging;
import org.example.scheduledweb.schedule.entity.Schedule;

import java.util.List;

public interface ScheduleRepository {

    ScheduleResponseDto saveSchedule(Schedule schedule);

    List<ScheduleResponseDto> findAllSchedules();

    Schedule findScheduleById(long id);

    Schedule findScheduleUserIdById(long id);

    int updateUserNameOrToDoContent(long id, String toDoContent);

    int deleteSchedule (long id);

    List<ScheduleResponseDto> pagination(Paging paging);

    Integer findUserByUserId(long userId);
}
