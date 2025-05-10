package org.example.scheduledweb.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.scheduledweb.entity.Schedule;

@Getter
@AllArgsConstructor
public class ScheduleResponseDto {

    private Long scheduleId;
    private String userName;
    private String todoContent;

    public ScheduleResponseDto(Schedule schedule) {
        this.scheduleId = schedule.getScheduleId();
        this.userName = schedule.getUserName();
        this.todoContent = schedule.getTodoContent();
    }
}
