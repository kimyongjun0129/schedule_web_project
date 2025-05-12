package org.example.scheduledweb.user.dto;

import jakarta.validation.constraints.Email;
import lombok.Getter;

@Getter
public class UserRequestDto {

    @Email(message = "올바른 형식의 이메일 주소가 아닙니다.")
    private String email;
    private String userName;
}
