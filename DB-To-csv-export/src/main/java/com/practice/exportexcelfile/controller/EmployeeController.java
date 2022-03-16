package com.practice.exportexcelfile.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.practice.exportexcelfile.dto.EmployeeDto;
import com.practice.exportexcelfile.model.Employee;
import com.practice.exportexcelfile.response.ResponseMessage;
import com.practice.exportexcelfile.service.CsvExportService;

@RestController
public class EmployeeController {

	@Autowired
	private final CsvExportService csvExportService;

	public EmployeeController(CsvExportService csvExportService) {
		this.csvExportService = csvExportService;
	}

	@PostMapping("/employee")
	public ResponseEntity<ResponseMessage> addEmployee(@RequestBody EmployeeDto employeeDto) {
		Employee saveEmployee = csvExportService.saveEmployee(employeeDto);
		ResponseMessage responseMessage = new ResponseMessage(HttpStatus.OK.value(), new java.util.Date(), false,
				saveEmployee.getFirstName() + " you have registered Successfully..", saveEmployee);
		return new ResponseEntity<>(responseMessage, HttpStatus.OK);
	}

	@GetMapping("/employees")
	public void getAllEmployeesInCsv(HttpServletResponse servletResponse) throws IOException {
		servletResponse.setContentType("text/csv");
		servletResponse.addHeader("Content-Disposition", "attachment; filename=\"employees.csv\"");
		csvExportService.writeEmployeesToCsv(servletResponse.getWriter());
	}
}
