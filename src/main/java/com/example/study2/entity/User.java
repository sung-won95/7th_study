package com.example.study2.entity;

import com.example.study2.dto.UserDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String password;

    @Column
    private String uuid;

    public User(UserDTO userDTO){
        this.name = userDTO.getName();
        this.password = userDTO.getPassword();
    }

    public void createUUID(){
        this.uuid = UUID.randomUUID().toString();
    }
}
