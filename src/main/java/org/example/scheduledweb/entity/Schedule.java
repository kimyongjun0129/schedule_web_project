package org.example.scheduledweb.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class Schedule {

    private Long scheduleId;
    private Long userId;
    private String userName;
    private String todoContent;
    private Date createAt;
    private Date updateAt;

    public Schedule(String userName, String todoContent) {
        this.userName = userName;
        this.todoContent = todoContent;
    }

    public Schedule(Long scheduleId, String userName, String todoContent) {
        this.scheduleId = scheduleId;
        this.userName = userName;
        this.todoContent = todoContent;
    }
}
