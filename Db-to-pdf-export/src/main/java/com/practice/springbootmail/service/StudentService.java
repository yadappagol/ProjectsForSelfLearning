package com.practice.springbootmail.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.springbootmail.model.Student;
import com.practice.springbootmail.repository.StudentRepository;

@Service
public class StudentService {
	@Autowired
	private StudentRepository repo;

	public List<Student> findAllStudents() {
		return repo.findAll();
	}

	public void save(Student student) {
		repo.save(student);
	}
}
