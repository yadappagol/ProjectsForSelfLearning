package com.practice.sendingmail.service;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.practice.sendingmail.exception.InvalidDetailsException;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private JavaMailService javaMailService;

	@Override
	@Scheduled(fixedRate =1*30*1000)
	public String sendFormLink() {
		String mailId="muteeqkhan.mk@gmail.com";
		String formLink = "https://1drv.ms/x/s!AmalJNvVaNIoaeXuCDjEs3xCA2I?e=FmRouR";

		String message = "Hi, \n Please Fill The Follwing Form by clicking following form link : " + formLink;

		String subject = "Regarding Filling The Form";
		try {
			javaMailService.sendOtpToMail(mailId, subject, message);
		} catch (MessagingException e) {
			throw new InvalidDetailsException("Invalid Details..");
		}
		return "Form Link sent to the  " + mailId + "   please check..";
	}

}
