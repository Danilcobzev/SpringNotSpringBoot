package org.example.servise_test;


import org.example.dto.UserPOJO;
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
        Assertions.assertEquals(new UserPOJO("Danil", "passw")
                , userService.getUserPOJOFromBody("Danil passw"));
    }

    @Test
    void getAll() {
        List<UserPOJO> users = new ArrayList<>();
        users.add(new UserPOJO("Danil", "passw"));
        users.add(new UserPOJO("Denis", "password"));
        Mockito.when(userRepo.findAll()).thenReturn(users);
        Assertions.assertArrayEquals(users.toArray(), userService.getAll().toArray());
    }
    @Test
    void authoriseOk(){
        List<UserPOJO> users = new ArrayList<>();
        users.add(new UserPOJO("Danil", "passw"));
        users.add(new UserPOJO("Denis", "password"));
        Mockito.when(userRepo.findAll()).thenReturn(users);
        Assertions.assertTrue(userService.authorise(new UserPOJO("Danil", "passw")));
    }

    @Test
    void notAuthorise(){
        List<UserPOJO> users = new ArrayList<>();
        users.add(new UserPOJO("Danil", "passw"));
        users.add(new UserPOJO("Denis", "password"));
        Mockito.when(userRepo.findAll()).thenReturn(users);
        Assertions.assertFalse(userService.authorise(new UserPOJO("Dima", "passw")));
    }
    @Test
    void changePasswordOk(){
        List<UserPOJO> users = new ArrayList<>();
        users.add(new UserPOJO("Danil", "passw"));
        users.add(new UserPOJO("Denis", "password"));
        Mockito.when(userRepo.findAll()).thenReturn(users);
        Assertions.assertTrue(userService.changePassword("Danil passw newPassw"));
    }

    @Test
    void notChangePassword(){
        List<UserPOJO> users = new ArrayList<>();
        users.add(new UserPOJO("Danil", "passw"));
        users.add(new UserPOJO("Denis", "password"));
        Mockito.when(userRepo.findAll()).thenReturn(users);
        Assertions.assertFalse(userService.changePassword("Dima passw newPassw"));
    }

    @Test
    void save(){
        UserPOJO userPOJO = new UserPOJO("Danil", "passw");
        userService.save(userPOJO);
        Mockito.verify(userRepo,Mockito.times(1)).saveUser(userPOJO);
    }
}
