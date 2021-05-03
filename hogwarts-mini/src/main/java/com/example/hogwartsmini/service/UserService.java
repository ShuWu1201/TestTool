package com.example.hogwartsmini.service;

import com.example.hogwartsmini.dto.UserDto;
import org.springframework.stereotype.Component;


public interface UserService {

    public String login(UserDto userDto);
}
