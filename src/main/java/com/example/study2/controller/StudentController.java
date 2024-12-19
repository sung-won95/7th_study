package com.example.study2.controller;

import com.example.study2.entity.Student;
import com.example.study2.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @GetMapping("/{id}")
    public void findAllStudent(@PathVariable Long id){
        studentService.printStudentCourses(id);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id){
        studentService.deleteFirstEnrollment(id);
    }

}
