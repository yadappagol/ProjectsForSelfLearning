package com.practice.javatask.dto;

import java.util.List;

import com.practice.javatask.entity.Client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgencyDto {
	private Long agencyId;
	private String agencyName;
	private String address1;
	private String address2;
	private String state;
	private String city;
	private String phoneNumber;
	private List<Client> clients;
}
