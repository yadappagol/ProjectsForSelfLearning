package com.practice.dynamiccsv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.dynamiccsv.dto.InsertDto;
import com.practice.dynamiccsv.entity.Employee;
import com.practice.dynamiccsv.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@RequestMapping
@RestController
@Slf4j
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	/**
	 * URL : http://localhost:8090/create
	 */
	@PostMapping("/create")
	public Employee name(@RequestBody InsertDto insertDto) {
		return employeeService.saveEmployee(insertDto);
	}

	/**
	 * URL : http://localhost:8090/retrive/{id}
	 */
	@GetMapping("/retrive/{id}")
	public Employee getEmployeeById(@PathVariable("id") Integer empId) {
		log.debug(" >> UserController :  {} call : ", empId);
		return employeeService.getEmployeeById(empId);
	}

	/**
	 * URL : http://localhost:8090/update
	 */
	@PutMapping("/update")
	public String updateEmployee(@RequestBody Employee employee) {
		return employeeService.updateEmployee(employee);
	}

	/**
	 * URL : http://localhost:8090/delete/{id}
	 */
	@DeleteMapping("/delete/{id}")
	public String deleteEmployeeById(@PathVariable("id") Integer empId) {
		log.debug(" >> UserController :  {} call : ", empId);
		employeeService.deleteEmployeeById(empId);
		return "Employee Deleted Successfully....";
	}

}
