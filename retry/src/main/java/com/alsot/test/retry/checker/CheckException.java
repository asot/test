package com.alsot.test.retry.checker;

public class CheckException extends RuntimeException {
	private static final long serialVersionUID = -4776876323461306760L;

	public CheckException() {
	}

	public CheckException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CheckException(String message, Throwable cause) {
		super(message, cause);
	}

	public CheckException(String message) {
		super(message);
	}

	public CheckException(Throwable cause) {
		super(cause);
	}

}
