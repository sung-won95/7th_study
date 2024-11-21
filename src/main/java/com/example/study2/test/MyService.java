package com.example.study2.test;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MyService {

    MyRepository myRepository;

    public void createPSW(){
        PSW psw = new PSW(1L,"test");

        myRepository.save(psw);
    }
}
