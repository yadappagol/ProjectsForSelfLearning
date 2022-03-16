package com.practice.batchprocessing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.practice.batchprocessing.model.Contract;

@Repository
public interface ContractRepository extends JpaRepository<Contract, String> {

}
