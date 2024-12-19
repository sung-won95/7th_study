package com.example.study2.service;

import com.example.study2.dto.StudentDTO;
import com.example.study2.entity.Course;
import com.example.study2.entity.Enrollment;
import com.example.study2.entity.Student;
import com.example.study2.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    public void printStudentCourses(Long studentId) {
//        Student student = studentRepository.findById(studentId).orElseThrow();
        Student student = studentRepository.findStudentWithCourses(studentId);

        // 지연 로딩으로 인해 루프마다 쿼리가 발생합니다 (N+1 문제)
        for (Enrollment enrollment : student.getEnrollments()) {
            System.out.println("수강한 코스 제목: " + enrollment.getCourse().getTitle());
            System.out.println("수강 날짜: " + enrollment.getEnrolledDate());
        }

        System.out.println("-------------------");

        List<StudentDTO> studentDTOList = studentRepository.findStudentWithCourse(studentId);

        for(StudentDTO studentDTO : studentDTOList){
            System.out.println(studentDTO.getTitle());
            System.out.println(studentDTO.getEnrolledDate().toString());
        }
    }

    public void deleteFirstEnrollment(Long studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow(EntityNotFoundException::new);

        student.getEnrollments().remove(0);

        studentRepository.save(student);
    }

}
