package org.example.service;

import org.example.dto.UserDto;
import org.example.dto.UserDtoNewPassword;

import java.util.List;

public interface UserService {
    List<UserDto> getAll();
    void authorise(UserDto userDto);
    void changePassword(UserDtoNewPassword userDtoNewPassword);
    void save(UserDto userDto);
}
