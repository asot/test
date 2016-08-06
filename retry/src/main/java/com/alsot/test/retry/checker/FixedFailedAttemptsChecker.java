package com.alsot.test.retry.checker;

public class FixedFailedAttemptsChecker implements Checker {
	
	private int failedAttempts = 0;
	private int maxFailedAttempts;
	
	public FixedFailedAttemptsChecker(int maxFailedAttempts) {
		this.maxFailedAttempts = maxFailedAttempts;
	}

	@Override
	public void check() throws CheckException {
		if (failedAttempts >= maxFailedAttempts) {
			return;
		}
		failedAttempts++;
		throw new CheckException("failed attempt " + failedAttempts + " out of " + maxFailedAttempts);
	}

}
