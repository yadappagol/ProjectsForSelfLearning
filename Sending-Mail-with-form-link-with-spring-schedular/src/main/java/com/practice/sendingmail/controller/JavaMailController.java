package com.practice.sendingmail.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.practice.sendingmail.dto.RequestDto;
import com.practice.sendingmail.responseMessage.ResponseMessage;
import com.practice.sendingmail.service.UserService;

@RestController
public class JavaMailController {

	@Autowired
	private UserService userService;

	@GetMapping("/sendFormLink")
	public ResponseEntity<ResponseMessage> sendFormLink(@RequestBody RequestDto requestDto) {
		String sendFormLInk = userService.sendFormLink();
		ResponseMessage responseMessage = new ResponseMessage(HttpStatus.OK.value(), new Date(), false,
				"Form details..", sendFormLInk);
		return new ResponseEntity<>(responseMessage, HttpStatus.OK);
	}

}
