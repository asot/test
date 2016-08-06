package com.alsot.test.retry.boolchecker;

import org.junit.Assert;
import org.junit.Test;

import com.alsot.test.retry.boolchecker.BooleanChecker;
import com.alsot.test.retry.boolchecker.FixedFailedAttemptsBooleanChecker;
import com.alsot.test.retry.boolchecker.RetryBooleanChecker;

public class RetryBooleanCheckerTest {

	@Test
	public void testFailedRetries() {
		BooleanChecker fixedFailedAttemptsChecker = new FixedFailedAttemptsBooleanChecker(3);
		RetryBooleanChecker retryChecker = new RetryBooleanChecker(fixedFailedAttemptsChecker, 2);
		
		Assert.assertFalse(retryChecker.check());
	}
	
	@Test
	public void testSuccessfulRetries() {
		BooleanChecker fixedFailedAttemptsChecker = new FixedFailedAttemptsBooleanChecker(3);
		RetryBooleanChecker retryChecker = new RetryBooleanChecker(fixedFailedAttemptsChecker, 3);
		
		Assert.assertTrue(retryChecker.check());
	}

}
