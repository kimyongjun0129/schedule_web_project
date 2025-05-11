package org.example.scheduledweb.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class Schedule {

    private long scheduleId;
    private long password;
    private String userName;
    private String todoContent;
    private Date createAt;
    private Date updateAt;

    public Schedule(long password, String userName, String todoContent) {
        this.password = password;
        this.userName = userName;
        this.todoContent = todoContent;
    }

    public Schedule(String userName, String todoContent, Date updateAt) {
        this.userName = userName;
        this.todoContent = todoContent;
        this.updateAt = updateAt;
    }

    public Schedule(long password) {
        this.password = password;
    }
}
