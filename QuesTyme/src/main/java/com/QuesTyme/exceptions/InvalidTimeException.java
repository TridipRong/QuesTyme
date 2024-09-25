package com.QuesTyme.exceptions;

public class InvalidTimeException extends RuntimeException{

	public InvalidTimeException() {
		
	}
	
	public InvalidTimeException(String msg) {
		super(msg);
	}
	
}
