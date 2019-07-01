package com.neu.edu.exceptions;

public class PostsException extends Exception{

	public PostsException(String message, Throwable cause) {
		super("PostsException" + message, cause);
		
	}

	public PostsException(String message) {
		super("PostsException" + message);
		
	}
	
	

}
