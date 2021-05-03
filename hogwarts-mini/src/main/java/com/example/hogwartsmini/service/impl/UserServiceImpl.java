package com.example.hogwartsmini.service.impl;

import com.example.hogwartsmini.dto.UserDto;
import com.example.hogwartsmini.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public String login(UserDto userDto){

        System.out.println("userDto:" + userDto.getName());
        System.out.println("userDto:" + userDto.getPwd());

        return "login success!" + userDto.getName() + " --- " + userDto.getPwd();
    }
}
