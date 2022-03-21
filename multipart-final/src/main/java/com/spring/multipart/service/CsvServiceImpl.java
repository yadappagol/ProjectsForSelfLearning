package com.spring.multipart.service;

import java.io.PrintWriter;
import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.multipart.model.Employee;

@Service
public class CsvServiceImpl implements CsvService {

	@Override
	public void downloadCsvFile(PrintWriter printWriter, List<Employee> employees) {
		printWriter.write("Employee Id,FirstName,LastName\n");
		for (Employee employee : employees) {
			printWriter.write(employee.getId() + " ," + employee.getFirstName() + "," + employee.getLastName());
		}
	}

}
