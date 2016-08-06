package com.alsot.test.retry.checker;

import java.util.concurrent.Callable;

@FunctionalInterface
public interface Checker {

	void check() throws CheckException;
	
	default void execConditionally(Callable<Void> callback) {
		try {
			check();
			System.out.print("executing...");
			callback.call();
		} catch (CheckException ce) {
			System.out.print("not executing");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	default Checker and(Checker checker) {
		return () -> {
			check();
			checker.check();
		};
	}
	
	default Checker or(Checker checker) {
		return () -> {
			CheckException thrown = null;
			try {
				check();
			} catch (CheckException ce) {
				thrown = ce;
			}
			if (thrown == null) {
				return;
			}
			checker.check();
		};
	}
	
	default Checker negate() {
		return () -> {
			try {
				check();
			} catch (CheckException ce) {
				return;
			}
			throw new CheckException(this + " checker did not throw check exception...");
		};
	}
	
}
