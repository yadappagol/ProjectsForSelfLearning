package com.practice.exportexcelfile.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String firstName;
	private String lastName;
	private String email;
	private String department;

	public Employee(String firstName, String lastName, String email, String department) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.department = department;
	}

}
