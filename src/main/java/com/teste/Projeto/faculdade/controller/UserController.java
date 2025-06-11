package com.teste.Projeto.faculdade.controller;

import com.teste.Projeto.faculdade.model.User;
import com.teste.Projeto.faculdade.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user) {
        User loggedUser = userService.login(user);
        if (loggedUser == null) {
            return ResponseEntity.status(401).build();
        }
        return ResponseEntity.ok(loggedUser);
    }

    @PostMapping("/register")
    public User register(@RequestBody User user){
        return userService.registerUser(user);
    }
}
