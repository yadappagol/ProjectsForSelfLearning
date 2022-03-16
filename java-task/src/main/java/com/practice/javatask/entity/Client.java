package com.practice.javatask.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ClientDetails")
public class Client {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long clientId;
	private String clientName;
	private String email;
	private String phoneNumber;
	private Double totalBill;

}
