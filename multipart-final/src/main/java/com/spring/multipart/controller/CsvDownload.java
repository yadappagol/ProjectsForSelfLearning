package com.spring.multipart.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.multipart.model.Employee;
import com.spring.multipart.service.CsvService;

@RestController
public class CsvDownload {

	@Autowired
	private CsvService csvService;

	@GetMapping("/get")
	public String index() {
		return "Get Index";
	}

	
	@GetMapping("/download/employee.csv")
	public void downloadCsvFile(HttpServletResponse response) throws IOException {
		response.setContentType("text/csv");
		response.setHeader("Content-Disposition", "attachment; file=employee.csv");
		csvService.downloadCsvFile(response.getWriter(), createTestData());

	}

	private List<Employee> createTestData() {
		List<Employee> employees = new ArrayList<>();
		employees.add(new Employee(10, "Dby", "Dby"));
		employees.add(new Employee(20, "Dby", "Dby"));
		employees.add(new Employee(30, "Dby", "Dby"));
		employees.add(new Employee(0, "Dby", "Dby"));
		employees.add(new Employee(150, "Dby", "Dby"));
		return employees;
	}

}
