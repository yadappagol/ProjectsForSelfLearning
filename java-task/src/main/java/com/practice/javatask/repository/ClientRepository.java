package com.practice.javatask.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.practice.javatask.entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

	@Query(nativeQuery = true, value = "select * from client_details where total_bill>=(select max(total_bill) from client_details)")
	List<Client> findTopBillsClientDetails();

	Client findByClientName(String username);

}
