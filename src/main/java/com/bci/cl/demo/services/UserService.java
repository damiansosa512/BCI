package com.bci.cl.demo.services;

import com.bci.cl.demo.dto.UserDto;
import com.bci.cl.demo.dto.response.InsertUserDto;
import com.bci.cl.demo.dto.response.UpdateUserDto;
import com.bci.cl.demo.entity.UserEntity;

import java.util.UUID;

public interface UserService {

    public InsertUserDto insertTransaction(UserDto userDto, String authorization);

    public UserDto getTransaction(UUID uuid);

    public String deleteTransaction(UUID uuid);

    public UpdateUserDto updateTransaction(UserDto userDto, String authorization);
}
