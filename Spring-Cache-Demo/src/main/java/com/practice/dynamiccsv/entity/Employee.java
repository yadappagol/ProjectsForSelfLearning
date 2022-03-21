package com.practice.dynamiccsv.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Integer empId;
	private String empName;
	private String empAddress;
	private Long empPhoneNumber;

}
