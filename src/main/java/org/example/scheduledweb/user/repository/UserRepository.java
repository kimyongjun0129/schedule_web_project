package org.example.scheduledweb.user.repository;

import org.example.scheduledweb.user.entity.User;
import org.example.scheduledweb.user.dto.UserResponseDto;

public interface UserRepository {

    UserResponseDto saveUser(User user);
}
