package com.example.study2.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@NamedEntityGraph(name = "Student.withEnrollmentsAndCourses",
        attributeNodes = @NamedAttributeNode(value = "enrollments", subgraph = "enrollmentsWithCourse"),
        subgraphs = @NamedSubgraph(name = "enrollmentsWithCourse", attributeNodes = @NamedAttributeNode("course")))
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    // 연관관계 설정
    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
    private List<Enrollment> enrollments = new ArrayList<>();

    // Getter, Setter
}