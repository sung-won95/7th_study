package com.example.study2.test;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyRepository extends JpaRepository<PSW,Long> {
    PSW findByName(String name);
}
