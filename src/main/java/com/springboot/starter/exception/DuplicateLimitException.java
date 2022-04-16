package com.springboot.starter.exception;




public class DuplicateLimitException extends RuntimeException {

	private static final long serialVersionUID = 4581945985633402857L;

	public DuplicateLimitException(String msg) {
        super(msg);
    }
}
