package com.example.study2.service;

import com.example.study2.dto.UserDTO;
import com.example.study2.entity.User;
import com.example.study2.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    final UserRepository userRepository;

    UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User createUser(UserDTO userDTO){
        User user = new User(userDTO);

        return userRepository.save(user);
    }

    @Transactional
    public String login(UserDTO userDTO) throws Exception {
        User user = userRepository.findByName(userDTO.getName()).orElseThrow(EntityNotFoundException::new);

        if(!user.getPassword().equals(userDTO.getPassword())){
            throw new Exception();
        }

        user.createUUID();

        return user.getUuid();
    }

    public User checkUserBySession(String uuid){
        return userRepository.findByUuid(uuid).orElseThrow(EntityNotFoundException::new);
    }

    public List<User> findAllUserByDatabase(){
        return userRepository.findAll();
    }

    public User findOneUser(Long id){
        return  userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public User updateUserById(Long id, UserDTO userDTO){
        User findUser = this.findOneUser(id);

        findUser.updateByDTO(userDTO);
        userRepository.save(findUser);

        return findUser;
    }

    public void deleteUserById(Long id){
        userRepository.deleteById(id);
    }


}
