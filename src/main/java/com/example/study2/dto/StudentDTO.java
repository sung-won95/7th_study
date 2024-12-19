package com.example.study2.dto;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class StudentDTO {

    private String title;
    private LocalDate enrolledDate;

    public StudentDTO(String title, LocalDate enrolledDate) {
        this.title = title;
        this.enrolledDate = enrolledDate;
    }
}
