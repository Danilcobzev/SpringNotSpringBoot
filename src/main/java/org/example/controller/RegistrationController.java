package org.example.controller;

import org.example.dto.LoginForm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RegistrationController {
    @PostMapping("/registration")
    public ResponseEntity<String> registration(@RequestBody(required = false) LoginForm loginForm){
        return ResponseEntity.ok("user is registered");
    }

}
