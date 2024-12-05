package com.example.study2.controller;

import com.example.study2.dto.UserDTO;
import com.example.study2.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    final UserService userService;

    AuthController(UserService userService){
        this.userService = userService;
    }


    @GetMapping("/session")
    public String test(HttpServletRequest request){
        HttpSession returnString = request.getSession(false);

        if(request.getSession(false)==null){
            return "세션이 없습니다.";
        }

        String sessionUuid = returnString.getAttribute("mySession").toString();

        return "유저 " + userService.checkUserBySession(sessionUuid).getName() + "가 접속했습니다.";
    }

    @PostMapping("/session")
    public String createSession(@RequestBody UserDTO userDTO, HttpServletRequest request) throws Exception {
        HttpSession httpSession = request.getSession(true);

        String uuid = userService.login(userDTO);

        httpSession.setAttribute("mySession",uuid);

        return "세션이 생성되었습니다!";
    }
}
