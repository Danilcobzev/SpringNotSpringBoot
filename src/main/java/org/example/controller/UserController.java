package org.example.controller;

import org.example.dto.UserDto;
import org.example.dto.UserDtoNewPassword;
import org.example.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/authorise")
    public ResponseEntity<String> authorise(@RequestBody UserDto user) {
        userService.authorise(user);
        return ResponseEntity.ok("user is authorized");
    }

    @GetMapping("/getall")
    public ResponseEntity<List<UserDto>> getAll() {
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(userService.getAll());
    }

    @PostMapping("/changePassword")
    public ResponseEntity<String> changePassword(@RequestBody UserDtoNewPassword userDtoNewPassword) {
        userService.changePassword(userDtoNewPassword);
        return ResponseEntity.ok("password changed");
    }
}
