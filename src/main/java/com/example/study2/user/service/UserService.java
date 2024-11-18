package com.example.study2.user.service;

import com.example.study2.user.entity.User;
import com.example.study2.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> readAllUser(){
        return  userRepository.getAllUsers();
    }

    public User createUser(User saveUser){
        int id = userRepository.createUser2(saveUser);
        System.out.println("저장된 ID는 " + id + "입니다.");

        return null;
    }

}
