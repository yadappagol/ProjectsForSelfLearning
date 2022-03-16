package com.practice.javatask.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.javatask.dto.AgencyDto;
import com.practice.javatask.dto.ClientDto;
import com.practice.javatask.exception.InvalidDetailsException;
import com.practice.javatask.response.ResponseMessage;
import com.practice.javatask.service.JavaTaskService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/v1/agency")
public class AgencyController {

	@Autowired
	private JavaTaskService javaTaskService;

	@PostMapping("/createUser")
	public ResponseEntity<ResponseMessage> createUser(@RequestBody AgencyDto agencyDto) {

		if (agencyDto != null) {
			AgencyDto createUser = javaTaskService.createUser(agencyDto);
			log.info(" Client Data Created Successfully");
			ResponseMessage responseMessage = new ResponseMessage(HttpStatus.OK.value(), new java.util.Date(), false,
					createUser.getClients().get(0).getClientName() + "created Successfully", agencyDto);
			return new ResponseEntity<>(responseMessage, HttpStatus.OK);
		}
		log.error("User Details are not Stored Into Db");
		throw new InvalidDetailsException("Something Went Wrong,Details are wrong");

	}

	@GetMapping("/fetch-highest-totalbill")
	public ResponseEntity<ResponseMessage> getByClientId() {
		List<ClientDto> findTopBillsClientDetails = javaTaskService.findTopBillsClientDetails();
		log.info("Fetched Successfully");
		ResponseMessage responseMessage = new ResponseMessage(HttpStatus.OK.value(), new java.util.Date(), false,
				"Fetched Successfully", findTopBillsClientDetails);
		return new ResponseEntity<>(responseMessage, HttpStatus.OK);

	}

}
