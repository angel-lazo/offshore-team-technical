package com.springboot.starter.exception;

public class NotFoundException extends RuntimeException {

	private static final long serialVersionUID = 6603591910143387395L;

	public NotFoundException() {
        super();
    }

    public NotFoundException(String msg) {
        super(msg);
    }
}
