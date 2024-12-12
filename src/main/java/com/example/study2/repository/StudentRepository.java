package com.example.study2.repository;

import com.example.study2.entity.Student;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("SELECT s FROM Student s JOIN FETCH s.enrollments e JOIN FETCH e.course WHERE s.id = :id")
    Student findStudentWithCourses(@Param("id") Long id);


    @EntityGraph("Student.withEnrollmentsAndCourses")
    Optional<Student> findById(Long id);
}
