package com.practice.springbatch.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Employee {
	@Id
	private Integer empId;
	private String name;
	private String surname;
	private Date creationDate;
}
