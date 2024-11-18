package com.example.study2.user.controller;

import com.example.study2.user.entity.User;
import com.example.study2.user.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    final UserService userService;

    UserController(UserService userService){
        this.userService = userService;
    }
    List<User> userList = new ArrayList<>();

    @GetMapping("/user/{userId}")
    public User readUser(@PathVariable Integer userId){
        return this.userList.get(userId);
    }

    @GetMapping("/user")
    public List<User> readUser1(){
        return  userService.readAllUser();
    }

    @PostMapping("/user")
    public User createUser(@RequestBody User user){
        userService.createUser(user);

        return user;
    }

    @PutMapping("/user/{userId}")
    public User updateUser(@RequestBody User user, @PathVariable Integer userId){
        this.userList.remove(userId.intValue());
        this.userList.add(userId,user);

        return this.userList.get(userId.intValue());
    }

}
