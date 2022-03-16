package com.practice.batchprocessing.batchconfig;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.batchprocessing.model.Contract;
import com.practice.batchprocessing.model.ContractHistory;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class ItemProcessorConfiguration {

	private AtomicInteger count = new AtomicInteger();

	private ObjectMapper objectMapper = new ObjectMapper();

	@Bean
	public ItemProcessor<Contract, ContractHistory> itemProcessor() {

		System.out.println(
				"*********************Method Inside The ItemProcessorConfiguration class,insede bean method *******************************");

		return new ItemProcessor<Contract, ContractHistory>() {

			@Override
			public ContractHistory process(Contract contract) throws Exception {
				log.info("processing the data " + contract.getContractId() + "Record No :" + count.incrementAndGet());
				return objectMapper.convertValue(contract, ContractHistory.class);
			}

		};

	}

}
