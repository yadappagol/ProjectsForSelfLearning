package com.practice.dynamiccsv.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.practice.dynamiccsv.dto.InsertDto;
import com.practice.dynamiccsv.entity.Employee;
import com.practice.dynamiccsv.repository.EmployeeRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public Employee saveEmployee(InsertDto insertDto) {
		Employee employee = new Employee();
		BeanUtils.copyProperties(insertDto, employee);
		return employeeRepository.save(employee);
	}

	@Override
	@Cacheable(cacheNames = "employee", key = "#id")
	public Employee getEmployeeById(int id) {
		log.info("This is Fetching data From DB For Id : {}");
		Optional<Employee> employee = employeeRepository.findById(id);
		if (employee.isPresent()) {
			return employeeRepository.findById(id).get();
		} else {
			return new Employee();
		}
	}

	@Override
	@CachePut(cacheNames = "employee")
	public String updateEmployee(Employee employee) {
		Employee employee2 = new Employee();
		BeanUtils.copyProperties(employee, employee2);
		employeeRepository.save(employee2);
		return "Employee Details are Updated Successfully...";
	}

	@Override
	@CacheEvict(cacheNames = "employee", key = "#id", allEntries = true)
	public String deleteEmployeeById(Integer empId) {
		employeeRepository.deleteById(empId);
		return "Employee Deleted Successfully.........";
	}

}
