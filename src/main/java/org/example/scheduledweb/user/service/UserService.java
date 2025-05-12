package org.example.scheduledweb.user.service;

import org.example.scheduledweb.user.dto.UserRequestDto;
import org.example.scheduledweb.user.dto.UserResponseDto;

public interface UserService {

    UserResponseDto saveUser(UserRequestDto requestDto);
}
