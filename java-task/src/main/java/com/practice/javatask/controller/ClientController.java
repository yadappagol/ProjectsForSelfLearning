package com.practice.javatask.controller;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.javatask.dto.UpdateClientDto;
import com.practice.javatask.entity.Client;
import com.practice.javatask.exception.InvalidDetailsException;
import com.practice.javatask.response.ResponseMessage;
import com.practice.javatask.service.JavaTaskService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/v1/client")
public class ClientController {

	@Autowired
	private JavaTaskService javaTaskService;

	@PutMapping("/updateClient")
	public ResponseEntity<ResponseMessage> updateClientById(@RequestBody UpdateClientDto client) {
		if (client != null) {
			String update = javaTaskService.updateClientById(client);
			log.info("Updated Successfully");
			ResponseMessage responseMessage = new ResponseMessage(HttpStatus.OK.value(), new java.util.Date(), false,
					"Created Successfully", update);
			return new ResponseEntity<>(responseMessage, HttpStatus.OK);
		}
		log.error("User Details are not Stored Into Db");
		throw new InvalidDetailsException("Something Went Wrong,Details are wrong");

	}

	@GetMapping("/token/refresh")
	public void refreashToken(HttpServletRequest request, HttpServletResponse response)
			throws JsonGenerationException, JsonMappingException, IOException {
		String header = request.getHeader(AUTHORIZATION);
		if (header != null && header.startsWith("Bearer ")) {
			try {
				String refresh_token = header.substring("Bearer ".length());
				Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
				JWTVerifier verifier = JWT.require(algorithm).build();
				DecodedJWT decodedJWT = verifier.verify(refresh_token);
				String username = decodedJWT.getSubject();
				Client client = javaTaskService.findByClientName(username);
				String access_token = JWT.create().withSubject(client.getClientName())
						.withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
						.withIssuer(request.getRequestURI().toString())
						.withClaim("roles", new ArrayList<>().add(new SimpleGrantedAuthority("USER"))).sign(algorithm);
				HashMap<String, String> tokens = new HashMap<>();
				tokens.put("access_token", access_token);
				tokens.put("refresh_token", refresh_token);
				response.setContentType(MediaType.APPLICATION_JSON_VALUE);
				new ObjectMapper().writeValue(response.getOutputStream(), tokens);
			} catch (Exception exception) {
				response.setHeader("error", exception.getMessage());
				response.setStatus(FORBIDDEN.value());
				HashMap<String, String> error = new HashMap<>();
				error.put("error", exception.getMessage());
				response.setContentType(MediaType.APPLICATION_JSON_VALUE);
				new ObjectMapper().writeValue(response.getOutputStream(), error);
			}
		} else
			throw new RuntimeException("Refresh token is missing");

	}

}
