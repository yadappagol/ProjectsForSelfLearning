package com.practice.springbootmail;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import com.practice.springbootmail.controller.EmailSenderService;

@SpringBootApplication
public class SendingMailUsingSpringbootMailApplication {

	@Autowired
	private EmailSenderService service;

	@EventListener(ApplicationReadyEvent.class)
	public void triggerMail() throws MessagingException {

		service.sendEmailWithAttachment("yadappagol@gmail.com", "This is Email Body with Attachment...",
				"This email has attachment", "E:\\REFERENCE_PROJECTS\\Multi-Part-Files\\dby.jpg");

	}

	public static void main(String[] args) {
		SpringApplication.run(SendingMailUsingSpringbootMailApplication.class, args);
	}

}
