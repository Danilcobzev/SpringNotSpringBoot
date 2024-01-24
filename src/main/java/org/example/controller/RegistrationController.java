package org.example.controller;

import org.example.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registration")
    public ResponseEntity<String> registration(@RequestBody(required = false) String body) {
        userService.save(userService.getUserPOJOFromBody(body));
        return ResponseEntity.ok("user is registered");
    }

}
