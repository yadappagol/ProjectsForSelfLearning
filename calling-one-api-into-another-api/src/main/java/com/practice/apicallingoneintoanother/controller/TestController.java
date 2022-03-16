package com.practice.apicallingoneintoanother.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.practice.apicallingoneintoanother.entity.Student;

@RestController
public class TestController {
	@GetMapping(value = "/student")
	public Student getStudent() {
		return new Student(1, "name1");
	}

	@GetMapping(value = "/getstudentstring")
	private String getStudentString() {
		String uri = "http://localhost:8085/student";
		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject(uri, String.class);
		return result;
	}

	@GetMapping(value = "/getstudent")
	private Student getStudentObject() {
		String uri = "http://localhost:8085/student";
		RestTemplate restTemplate = new RestTemplate();
		Student result = restTemplate.getForObject(uri, Student.class);
		return result;
	}
}
