package org.example.scheduledweb.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class UserResponseDto {

    private Long userId;
    private String email;
    private String userName;
    private Date updateAt;
}
