package com.practice.dynamiccsv.service;

import com.practice.dynamiccsv.dto.InsertDto;
import com.practice.dynamiccsv.entity.Employee;

public interface EmployeeService {

	Employee saveEmployee(InsertDto insertDto);

	Employee getEmployeeById(int id);

	String updateEmployee(Employee employee);

	String deleteEmployeeById(Integer empId);

}
