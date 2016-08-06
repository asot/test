package com.alsot.test.retry.boolchecker;

public class FixedFailedAttemptsBooleanChecker implements BooleanChecker {
	
	private int failedAttempts = 0;
	private int maxFailedAttempts;
	
	public FixedFailedAttemptsBooleanChecker(int maxFailedAttempts) {
		this.maxFailedAttempts = maxFailedAttempts;
	}

	@Override
	public boolean check() {
		if (failedAttempts >= maxFailedAttempts) {
			return true;
		}
		failedAttempts++;
		return false;
	}

}
