package com.alsot.test.retry.checker;

import org.junit.Test;

import com.alsot.test.retry.checker.CheckException;
import com.alsot.test.retry.checker.Checker;

public class NegateCheckerTest {

	@Test(expected = CheckException.class)
	public void shouldThrowException() {
		//given
		Checker successfulChecker = () -> {
			return;
		};
		Checker failedChecker = successfulChecker.negate();
		
		//when
		failedChecker.check();
	}
	
	@Test()
	public void shouldNotThrowException() {
		//given
		Checker failChecker = () -> {
			throw new CheckException("fail checker...");
		};
		Checker successChecker = failChecker.negate();
		
		//when
		successChecker.check();
	}

}
