package com.practice.exportexcelfile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.practice.exportexcelfile.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
