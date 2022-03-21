package com.practice.dynamiccsv.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.dynamiccsv.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
