package com.spring.multipart.service;

import java.io.PrintWriter;
import java.util.List;

import com.spring.multipart.model.Employee;

public interface CsvService {
	public void downloadCsvFile(PrintWriter printWriter, List<Employee> employees);
}
