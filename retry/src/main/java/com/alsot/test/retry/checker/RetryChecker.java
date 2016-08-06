package com.alsot.test.retry.checker;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import com.nurkiewicz.asyncretry.AsyncRetryExecutor;
import com.nurkiewicz.asyncretry.RetryExecutor;

public class RetryChecker implements Checker {
	
	private Checker checker;
	private RetryExecutor retryExecutor;
	
	/**
	 * @param checker
	 * @param maxRetries : the max number of <strong>retries</strong>. The <strong>tries</strong> are <code>maxRetries + 1</code>
	 */
	public RetryChecker(Checker checker, int maxRetries) {
		this(checker, defaultExecutor(maxRetries));
	}

	public RetryChecker(Checker checker, RetryExecutor retryExecutor) {
		this.checker = checker;
		this.retryExecutor = retryExecutor;
	}
	
	private static RetryExecutor defaultExecutor(int maxRetries) {
		ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
		RetryExecutor executor = new AsyncRetryExecutor(scheduler)
										.retryOn(CheckException.class)
										.withExponentialBackoff(1000, 2)
										.withMaxRetries(maxRetries);
		return executor;
	}

	@Override
	public void check() throws CheckException {
		CompletableFuture<Void> future = retryExecutor.getWithRetry(() -> {
																		checker.check();
																		return null;
										});
		try {
			future.get();
		} catch (Exception e) {
			if (e.getCause() instanceof CheckException) {
				throw (CheckException)e.getCause();
			} else {
				throw new RuntimeException(e);
			}
		}
	}

}
