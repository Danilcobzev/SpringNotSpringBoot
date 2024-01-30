package org.example.servise_test;


import org.example.Exceptions.BadRequestException;
import org.example.Exceptions.UnauthorizedException;
import org.example.dto.UserDto;
import org.example.repos.UserRepo;
import org.example.service.serviceImpl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepo userRepo;

    @Test
    void getUserPOJOFromBody() {
        Assertions.assertEquals(new UserDto("Danil", "passw")
                , userService.getUserPOJOFromBody("Danil passw"));
    }

    @Test
    void getAll() {
        List<UserDto> users = new ArrayList<>();
        users.add(new UserDto("Danil", "passw"));
        users.add(new UserDto("Denis", "password"));
        Mockito.when(userRepo.findAll()).thenReturn(users);
        Assertions.assertArrayEquals(users.toArray(), userService.getAll().toArray());
    }
    @Test
    void authorise_ok(){
        List<UserDto> users = new ArrayList<>();
        users.add(new UserDto("Danil", "passw"));
        users.add(new UserDto("Denis", "password"));
        Mockito.when(userRepo.findAll()).thenReturn(users);
        userService.authorise(new UserDto("Danil", "passw"));
        Mockito.verify(userRepo,Mockito.times(1)).findAll();
    }

    @Test
    void authorise_badCredentials(){
        List<UserDto> users = new ArrayList<>();
        users.add(new UserDto("Danil", "passw"));
        users.add(new UserDto("Denis", "password"));
        Mockito.when(userRepo.findAll()).thenReturn(users);
        Assertions.assertThrows(UnauthorizedException.class,()->userService.authorise(new UserDto("Dima", "passw")));
    }
    @Test
    void changePassword_ok(){
        List<UserDto> users = new ArrayList<>();
        users.add(new UserDto("Danil", "passw"));
        users.add(new UserDto("Denis", "password"));
        Mockito.when(userRepo.findAll()).thenReturn(users);
        userService.changePassword("Danil passw newPassw");
        Mockito.verify(userRepo,Mockito.times(1)).findAll();
    }

    @Test
    void changePassword_badRequest(){
        List<UserDto> users = new ArrayList<>();
        users.add(new UserDto("Danil", "passw"));
        users.add(new UserDto("Denis", "password"));
        Mockito.when(userRepo.findAll()).thenReturn(users);
        Assertions.assertThrows(BadRequestException.class,()->userService.changePassword("Dima passw newPassw"));
    }

    @Test
    void save(){
        UserDto userDto = new UserDto("Danil", "passw");
        userService.save(userDto);
        Mockito.verify(userRepo,Mockito.times(1)).saveUser(userDto);
    }
}
