package com.practice.springbootmail.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.practice.springbootmail.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
