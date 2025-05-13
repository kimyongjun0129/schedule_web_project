package org.example.scheduledweb.schedule.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Timestamp;


@Getter
@AllArgsConstructor
public class Schedule {

    private long scheduleId;
    private long userId;
    private String userName;
    private String todoContent;
    private Timestamp createAt;
    private Timestamp updateAt;

    public Schedule(long userId, String todoContent) {
        this.userId = userId;
        this.todoContent = todoContent;
    }

    public Schedule(String userName, String todoContent, Timestamp updateAt) {
        this.userName = userName;
        this.todoContent = todoContent;
        this.updateAt = updateAt;
    }

    public Schedule(long userId) {
        this.userId = userId;
    }
}
