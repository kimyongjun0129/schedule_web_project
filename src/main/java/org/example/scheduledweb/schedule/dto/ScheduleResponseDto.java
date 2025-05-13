package org.example.scheduledweb.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.scheduledweb.schedule.entity.Schedule;

import java.util.Date;


@Getter
@AllArgsConstructor
public class ScheduleResponseDto {

    private String userName;
    private String todoContent;
    private Date updateAt;

    public ScheduleResponseDto(Schedule schedule) {
        this.userName = schedule.getUserName();
        this.todoContent = schedule.getTodoContent();
        this.updateAt = schedule.getUpdateAt();
    }
}
