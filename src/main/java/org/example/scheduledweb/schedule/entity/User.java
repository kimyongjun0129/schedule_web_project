package org.example.scheduledweb.schedule.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class User {

    private Long userId;
    private String email;
    private String userName;
    private Date createAt;
    private Date updateAt;

    public User(String email, String userName) {
        this.email = email;
        this.userName = userName;
    }
}
