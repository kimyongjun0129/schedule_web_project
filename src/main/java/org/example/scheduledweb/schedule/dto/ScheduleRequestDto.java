package org.example.scheduledweb.schedule.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class ScheduleRequestDto {


    @NotNull(message = "사용자 Id는 필수 입니다.")
    private Long userId;

    @Size(max=200, message = "할 일은 200자 내여야 합니다.")
    @NotNull(message = "할 일은 필수 입니다.")
    private String toDoContent;
}
