package com.practice.javatask.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {
	private Long clientId;
	private String clientName;
	private String email;
	private String phoneNumber;
	private Double totalBill;

}
