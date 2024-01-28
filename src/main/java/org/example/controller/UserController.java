package org.example.controller;

import org.example.dto.UserPOJO;
import org.example.service.UserService;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<String> authorise(@RequestBody(required = false) String body) {
        userService.authorise(userService.getUserPOJOFromBody(body));
        return ResponseEntity.ok("user is authorized");
    }

    @GetMapping("/getall")
    public ResponseEntity<List<UserPOJO>> getAll() {
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(userService.getAll());
    }

    @PostMapping("/test")
    public ResponseEntity<String> test(@RequestBody UserPOJO user) {
        //still not working
        return ResponseEntity.ok("conflict");
    }

    @PostMapping("/changePassword")
    public ResponseEntity<String> changePassword(@RequestBody(required = false) String body) {
        userService.changePassword(body);
        return ResponseEntity.ok("password changed");
    }
}
