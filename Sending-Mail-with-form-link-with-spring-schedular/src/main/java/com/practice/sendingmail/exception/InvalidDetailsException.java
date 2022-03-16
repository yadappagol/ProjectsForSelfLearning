package com.practice.sendingmail.exception;

@SuppressWarnings("serial")
public class InvalidDetailsException extends RuntimeException {

	public InvalidDetailsException(String message) {
		super(message);
	}

}
