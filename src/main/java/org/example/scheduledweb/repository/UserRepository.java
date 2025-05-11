package org.example.scheduledweb.repository;

import org.example.scheduledweb.dto.UserResponseDto;
import org.example.scheduledweb.entity.User;

public interface UserRepository {

    UserResponseDto saveUser(User user);
}
