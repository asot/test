package com.alsot.test.retry.boolchecker;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import com.nurkiewicz.asyncretry.AsyncRetryExecutor;
import com.nurkiewicz.asyncretry.RetryExecutor;

public class RetryBooleanChecker implements BooleanChecker {
	
	private BooleanChecker checker;
	private RetryExecutor retryExecutor;
	
	/**
	 * @param checker
	 * @param maxRetries : the max number of <strong>retries</strong>. The <strong>tries</strong> are <code>maxRetries + 1</code>
	 */
	public RetryBooleanChecker(BooleanChecker checker, int maxRetries) {
		this(checker, defaultExecutor(maxRetries));
	}

	public RetryBooleanChecker(BooleanChecker checker, RetryExecutor retryExecutor) {
		this.checker = checker;
		this.retryExecutor = retryExecutor;
	}
	
	private static RetryExecutor defaultExecutor(int maxRetries) {
		ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
		RetryExecutor executor = new AsyncRetryExecutor(scheduler)
										.retryOn(BooleanCheckException.class)
										.withExponentialBackoff(1000, 2)
										.withMaxRetries(maxRetries);
		return executor;
	}

	@Override
	public boolean check() {
		CompletableFuture<Boolean> future = retryExecutor.getWithRetry(() -> {
											if (!checker.check()) throw new BooleanCheckException();
											return true;
										});
		try {
			return future.get();
		} catch (Exception e) {
			e.printStackTrace(System.err);
			return false;
		}
	}

}
