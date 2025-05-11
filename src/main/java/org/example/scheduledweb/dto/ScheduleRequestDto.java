package org.example.scheduledweb.dto;

import lombok.Getter;

@Getter
public class ScheduleRequestDto {

    private Long password;
    private String userName;
    private String toDoContent;
}
