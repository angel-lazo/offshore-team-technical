package com.springboot.starter.exception;

public class InvalidRequestException extends RuntimeException {

	private static final long serialVersionUID = -8615334587709453946L;

	public InvalidRequestException() {
        super();
    }

    public InvalidRequestException(String message) {
        super(message);
    }
}
