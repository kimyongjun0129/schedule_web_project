package org.example.scheduledweb.user.service;

import org.example.scheduledweb.schedule.entity.User;
import org.example.scheduledweb.user.repository.UserRepository;
import org.example.scheduledweb.user.dto.UserRequestDto;
import org.example.scheduledweb.user.dto.UserResponseDto;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponseDto saveUser(UserRequestDto requestDto) {
        User user = new User(requestDto.getEmail(), requestDto.getUserName());
        return userRepository.saveUser(user);
    }
}
