package org.example.scheduledweb.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.scheduledweb.schedule.entity.Schedule;

import java.sql.Timestamp;


@Getter
@AllArgsConstructor
public class ScheduleResponseDto {

    private String userName;
    private String todoContent;
    private Timestamp updateAt;

    public ScheduleResponseDto(Schedule schedule) {
        this.userName = schedule.getUserName();
        this.todoContent = schedule.getTodoContent();
        this.updateAt = schedule.getUpdateAt();
    }
}
