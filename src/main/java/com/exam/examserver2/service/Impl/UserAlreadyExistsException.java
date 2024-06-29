package com.exam.examserver2.service.Impl;

public class UserAlreadyExistsException extends Exception {
	public UserAlreadyExistsException(String message) {
        super(message);
    }

}
