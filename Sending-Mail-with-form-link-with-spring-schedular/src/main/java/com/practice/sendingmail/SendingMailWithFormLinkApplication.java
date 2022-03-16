package com.practice.sendingmail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SendingMailWithFormLinkApplication {

	public static void main(String[] args) {
		SpringApplication.run(SendingMailWithFormLinkApplication.class, args);
	}

}
