package com.example.study2.controller;

import com.example.study2.dto.UserDTO;
import com.example.study2.entity.User;
import com.example.study2.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    final UserService userService;

    UserController(UserService userService){
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

    //C -> POST /v1/users
    @PostMapping
    public User createUser(@RequestBody UserDTO userDTO){

        return userService.createUser(userDTO);
    }


    //R -> GET /v1/users ,
    @GetMapping
    public List<User> findAllUser(){
        return userService.findAllUserByDatabase();
    }

    //GET /v1/users/{id}

    @GetMapping("/{id}")
    public User findOneUser(@PathVariable Long id){
        return userService.findOneUser(id);
    }

    //U ->
    @PutMapping("/{id}")
    public String updateUser(@PathVariable Long id,
                             @RequestBody UserDTO userDTO,
                             HttpServletRequest httpServletRequest
                             ){

        HttpSession httpSession = httpServletRequest.getSession(false );
        if (httpSession == null){
            return "로그인해주세요.";
        }
        User findUser = userService.checkUserBySession(httpSession.getAttribute("mySession").toString());

        if(!findUser.getId().equals(id)){
            return "권한이 없습니다.";
        }
        new ResponseEntity<Void>(HttpStatus.OK);

        userService.updateUserById(id,userDTO);
        return "업데이트가 정상적으로 완료되었습니다.";
    }


    //D ->
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id,
                           HttpServletRequest httpServletRequest){
        HttpSession httpSession = httpServletRequest.getSession(false );
        if (httpSession == null){
            return "로그인해주세요.";
        }
        User findUser = userService.checkUserBySession(httpSession.getAttribute("mySession").toString());

        if(!findUser.getId().equals(id)){
            return "권한이 없습니다.";
        }

        userService.deleteUserById(id);
        return "삭제가 완료되었습니다.";
    }

}
