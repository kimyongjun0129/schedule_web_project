package org.example.scheduledweb.service;

import org.example.scheduledweb.dto.UserRequestDto;
import org.example.scheduledweb.dto.UserResponseDto;

public interface UserService {

    UserResponseDto saveUser(UserRequestDto requestDto);
}
