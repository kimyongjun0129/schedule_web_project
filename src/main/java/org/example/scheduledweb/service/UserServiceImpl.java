package org.example.scheduledweb.service;

import org.example.scheduledweb.dto.UserRequestDto;
import org.example.scheduledweb.dto.UserResponseDto;
import org.example.scheduledweb.entity.User;
import org.example.scheduledweb.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponseDto saveUser(UserRequestDto requestDto) {
        User user = new User(requestDto.getUserName(), requestDto.getEmail());
        return userRepository.saveUser(user);
    }
}
