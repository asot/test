package com.alsot.test.retry.checker;

import org.junit.Test;

import com.alsot.test.retry.checker.CheckException;
import com.alsot.test.retry.checker.Checker;
import com.alsot.test.retry.checker.FixedFailedAttemptsChecker;
import com.alsot.test.retry.checker.RetryChecker;

public class RetryCheckerTest {
	
	@Test(expected = CheckException.class)
	public void testFailedRetries() {
		Checker fixedFailedAttemptsChecker = new FixedFailedAttemptsChecker(3);
		RetryChecker retryChecker = new RetryChecker(fixedFailedAttemptsChecker, 2);
		
		retryChecker.check();
		
	}
	
	@Test
	public void testSuccessfulRetries() {
		Checker fixedFailedAttemptsChecker = new FixedFailedAttemptsChecker(3);
		RetryChecker retryChecker = new RetryChecker(fixedFailedAttemptsChecker, 3);
		
		retryChecker.check();
	}
	
}
