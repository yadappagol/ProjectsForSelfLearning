package com.practice.javatask.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "AgencyDetails")
public class Agency {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long agencyId;
	private String agencyName;
	private String address1;
	private String address2;
	private String state;
	private String city;
	private String phoneNumber;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "agencyId")
	private List<Client> clients;

}
