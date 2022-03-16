package com.practice.springbootmail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.practice.springbootmail.model.Student;
import com.practice.springbootmail.service.StudentService;

@SpringBootApplication
public class DbToPdfExportApplication implements CommandLineRunner {

	@Autowired
	public StudentService service;

	public static void main(String[] args) {
		SpringApplication.run(DbToPdfExportApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		for (int i = 0; i < 11; i++) {
			Student student = new Student();
			student.setName("Student " + i);
			student.setSection("Section " + i);
			service.save(student);
		}

	}
}
