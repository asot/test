package com.alsot.test.retry.boolchecker;

import java.util.function.Function;

@FunctionalInterface
public interface BooleanChecker {

	boolean check();
	
	default void execConditionally(Function<Void, Void> procedure) {
		if (check()) {
			procedure.apply(null);
		}
	}
	
	default BooleanChecker and(final BooleanChecker checker) {
		return () -> {
			return check() && checker.check();
		};
	}
	
	default BooleanChecker or(final BooleanChecker checker) {
		return () -> {
			return check() || checker.check();
		};
	}
	
	default BooleanChecker negate() {
		return () -> {
			return !check();
		};
	}
	
}
