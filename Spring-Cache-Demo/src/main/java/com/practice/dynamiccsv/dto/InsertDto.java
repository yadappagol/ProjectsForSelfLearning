package com.practice.dynamiccsv.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsertDto implements Serializable{
	private String empName;
	private String empAddress;
	private Long empPhoneNumber;
}
