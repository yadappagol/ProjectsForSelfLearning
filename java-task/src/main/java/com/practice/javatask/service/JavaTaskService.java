package com.practice.javatask.service;

import java.util.List;

import com.practice.javatask.dto.AgencyDto;
import com.practice.javatask.dto.ClientDto;
import com.practice.javatask.dto.FetchClient;
import com.practice.javatask.dto.UpdateClientDto;
import com.practice.javatask.entity.Client;

public interface JavaTaskService {

	AgencyDto createUser(AgencyDto agencyDto);

	String updateClientById(UpdateClientDto client);

	FetchClient getByClientId();

	List<ClientDto> findTopBillsClientDetails();

	Client findByClientName(String username);

}
