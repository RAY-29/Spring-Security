package com.vraj.springSecurity.controller;

import com.vraj.springSecurity.entity.User;
import com.vraj.springSecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getUser() {
        return userService.getAll();
    }

    @PostMapping
    public void createUser(@RequestBody User user) {
         userService.saveEntry(user);
    }

    @PutMapping
    public void updateUser(@RequestBody User user) {
        User existing = userService.findByUsername(user.getUserName());
        if(existing != null) {
            existing.setUserName(user.getUserName());
            existing.setPassword(user.getPassword());
            userService.saveEntry(existing);
        }
    }




}
