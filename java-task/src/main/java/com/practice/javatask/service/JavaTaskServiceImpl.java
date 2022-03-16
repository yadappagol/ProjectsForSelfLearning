package com.practice.javatask.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.practice.javatask.dto.AgencyDto;
import com.practice.javatask.dto.ClientDto;
import com.practice.javatask.dto.FetchClient;
import com.practice.javatask.dto.UpdateClientDto;
import com.practice.javatask.entity.Agency;
import com.practice.javatask.entity.Client;
import com.practice.javatask.exception.InvalidDetailsException;
import com.practice.javatask.repository.AgencyRepository;
import com.practice.javatask.repository.ClientRepository;

@Service
public class JavaTaskServiceImpl implements JavaTaskService, UserDetailsService {

	private static final Logger LOGGER = LoggerFactory.getLogger(JavaTaskServiceImpl.class);

	@Autowired
	private AgencyRepository agencyRepository;

	@Autowired
	private ClientRepository clientRepository;

	@Override
	public AgencyDto createUser(AgencyDto agencyDto) {
		Agency agency = new Agency();
		BeanUtils.copyProperties(agencyDto, agency);
		Agency save = agencyRepository.save(agency);
		BeanUtils.copyProperties(save, agencyDto);
		return agencyDto;
	}

	@Override
	public String updateClientById(UpdateClientDto clientDto) {
		Client client = clientRepository.getById(clientDto.getClientId());
		BeanUtils.copyProperties(clientDto, client);
		clientRepository.save(client);
		return "Your Details  Updated Successfully...";
	}

	@Override
	public FetchClient getByClientId() {
		FetchClient fetchClient = new FetchClient();
		return fetchClient;
	}

	@Override
	public List<ClientDto> findTopBillsClientDetails() {
		List<Client> findTopBillsClientDetails = clientRepository.findTopBillsClientDetails();
		List<ClientDto> clientDtos = new ArrayList<>();
		for (Client client : findTopBillsClientDetails) {
			ClientDto clientDto = new ClientDto();
			BeanUtils.copyProperties(client, clientDto);
			clientDtos.add(clientDto);
		}
		return clientDtos;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
		LOGGER.info("Loading UserName And Password inside AccountholderServiceImpl Class!!");
		if (!username.equals("Admin")) {

			Client client = clientRepository.findByClientName(username);
			if (client == null) {
				LOGGER.error("User Not in Data Base");
				throw new InvalidDetailsException("Please Enter your Correct User Name");
			}
			authorities.add(new SimpleGrantedAuthority("USER"));
			return new User(client.getClientName(), client.getPhoneNumber(), authorities);
		} else {
			Agency agency = agencyRepository.findByAgencyName(username);
			if (agency == null) {
				LOGGER.error("User Not in Data Base");
				throw new InvalidDetailsException("Please Enter your Correct User Name");
			}
			authorities.add(new SimpleGrantedAuthority("ADMIN"));
			return new User(agency.getAgencyName(), agency.getPhoneNumber(), authorities);
		}
	}

	@Override
	public Client findByClientName(String username) {
		return clientRepository.findByClientName(username);
	}

}
