package com.practice.batchprocessing.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.batchprocessing.model.Contract;
import com.practice.batchprocessing.repository.ContractRepository;

import lombok.SneakyThrows;

@RestController
public class BatchController {

	@Autowired
	private ContractRepository contractRepository;

	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private Job job;

	/**
	 * URL : http://localhost:8082/spring-batch/insert
	 *
	 */

	@PostMapping("/insert")
	public String saveDummyData() {

		System.out.println(
				"*********************Method Inside The BatchController class,inside saveDummyData method*******************************");
		List<Contract> contractList = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			Contract contract = new Contract();
			contract.setHolderName("name :- " + i);
			contract.setDuration(new Random().nextInt());
			contract.setAmount(new Random().nextInt(10000));
			Date date = new Date();
			date.setDate(new Random().nextInt(30));
			date.setMonth(new Random().nextInt(12));
			date.setYear(new Random().nextInt(2020));
			contract.setCreationDate(date);
			contract.setStatus("InProgress");
			contractList.add(contract);
		}
		contractRepository.saveAll(contractList);
		return "Data Saved Successsfully";

	}

	/**
	 * URL : http://localhost:8082/spring-batch/start-batch
	 *
	 */

	@GetMapping("/start-batch")
	@SneakyThrows
	public String startBatch() {
		System.out.println(
				"*********************Method Inside The BatchController class,inside startBatch method*******************************");

		JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis())
				.toJobParameters();

		jobLauncher.run(job, jobParameters);
		return "batch-started..........";
	}

}
