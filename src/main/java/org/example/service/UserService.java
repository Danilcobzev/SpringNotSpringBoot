package org.example.service;

import org.example.dto.UserPOJO;

import java.util.List;

public interface UserService {
    List<UserPOJO> getAll();
    UserPOJO getUserPOJOFromBody(String body);
    boolean authorise(UserPOJO userPOJO);
    boolean changePassword(String password);
    void save(UserPOJO userPOJO);
}
