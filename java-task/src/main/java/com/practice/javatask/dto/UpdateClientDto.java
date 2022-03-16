package com.practice.javatask.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateClientDto {
	private Long clientId;
	private String name;
	private String email;
	private String phoneNumber;
	private Double totalBill;
}
