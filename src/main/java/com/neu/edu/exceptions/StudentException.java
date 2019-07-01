package com.neu.edu.exceptions;

public class StudentException extends Exception{

	public StudentException(String message, Throwable cause) {
		super("StudentException" + message, cause);
		
	}

	public StudentException(String message) {
		super("StudentException" + message);
		
	}

	
	
}
