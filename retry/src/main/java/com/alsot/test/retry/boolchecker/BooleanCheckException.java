package com.alsot.test.retry.boolchecker;

public class BooleanCheckException extends RuntimeException {
	
	private static final long serialVersionUID = -6544825801264041391L;

	public BooleanCheckException() {
	}

	public BooleanCheckException(String message) {
		super(message);
	}

	public BooleanCheckException(Throwable cause) {
		super(cause);
	}

	public BooleanCheckException(String message, Throwable cause) {
		super(message, cause);
	}

	public BooleanCheckException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
