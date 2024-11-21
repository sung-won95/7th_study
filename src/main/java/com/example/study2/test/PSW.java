package com.example.study2.test;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PSW {

    @Id
    Long id;

    @Column
    String name;


    public void updateByDTO(PSWDTO pswdto){
        this.name = pswdto.getName();
    }
}
