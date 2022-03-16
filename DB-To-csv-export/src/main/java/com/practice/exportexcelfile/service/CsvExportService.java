package com.practice.exportexcelfile.service;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.exportexcelfile.dto.EmployeeDto;
import com.practice.exportexcelfile.model.Employee;
import com.practice.exportexcelfile.repository.EmployeeRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CsvExportService {

	@Autowired
	private final EmployeeRepository employeeRepository;

	public CsvExportService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	public void writeEmployeesToCsv(Writer writer) {

		List<Employee> employees = employeeRepository.findAll();
		try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
			for (Employee employee : employees) {
				csvPrinter.printRecord(employee.getId(), employee.getFirstName(), employee.getLastName(),
						employee.getEmail(), employee.getDepartment());
			}
		} catch (IOException e) {
			log.error("Error While writing CSV ", e);
		}
	}

	public Employee saveEmployee(EmployeeDto employeeDto) {
		Employee employee = new Employee(employeeDto.getFirstName(), employeeDto.getLastName(), employeeDto.getEmail(),
				employeeDto.getDepartment());
		return employeeRepository.save(employee);
	}
}
