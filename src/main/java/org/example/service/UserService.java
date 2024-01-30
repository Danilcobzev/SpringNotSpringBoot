package org.example.service;

import org.example.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getAll();
    UserDto getUserPOJOFromBody(String body);
    void authorise(UserDto userDto);
    void changePassword(String password);
    void save(UserDto userDto);
}
