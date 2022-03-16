package com.practice.javatask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.practice.javatask.entity.Agency;

@Repository
public interface AgencyRepository extends JpaRepository<Agency, Long> {


	Agency findByAgencyName(String username);

}
