package com.practice.javatask.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FetchClient {
	private String agencyName;
	private String clientName;
	private Double totalBill;
}
