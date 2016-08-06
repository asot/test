package com.alsot.test.retry.boolchecker

import spock.lang.Specification

class RetryBooleanCheckerSpec extends Specification {

	def "retry checker returns false after executing max retry attempts"() {
		given: "a checker that will fail the first 3 times that it is invoked"
			def fixedFailedAttemptsChecker = Mock BooleanChecker
		and: "a retry checker that will retry at most 2 times (3 times in total) before returning false"
			def retryChecker = new RetryBooleanChecker(fixedFailedAttemptsChecker, 2)
		
		when: "the retry checker is invoked"
		def result = retryChecker.check()
		
		then: "the result is false"
			result == false
		and: "the fixFailedAttemptsChecker check method has been invoked 3 times"
			3 * fixedFailedAttemptsChecker.check() >>> [false, false, false]
	}
	
	def "retry checker returns true in the last retry attempt"() {
		given: "a checker that will fail the first 3 times that it is invoked, and will succeed the 4th"
			def fixedFailedAttemptsChecker = Mock BooleanChecker
		and: "a retry checker that will retry at most 3 times (4 times in total) before returning false"
			def retryChecker = new RetryBooleanChecker(fixedFailedAttemptsChecker, 3)
		
		when: "the retry checker is invoked"
		def result = retryChecker.check()
		
		then: "the result is true"
			result == true
		and: "the fixFailedAttemptsChecker check method has been invoked 4 times"
			4 * fixedFailedAttemptsChecker.check() >>> [false, false, false, true]
	}
	
	def "retry checker returns true before executing max retry attempts"() {
		given: "a checker that will fail the first time that it is invoked, and will succeed the 2nd"
			def fixedFailedAttemptsChecker = Mock BooleanChecker
		and: "a retry checker that will retry at most 3 times (4 times in total) before returning false"
			def retryChecker = new RetryBooleanChecker(fixedFailedAttemptsChecker, 3)
		
		when: "the retry checker is invoked"
		def result = retryChecker.check()
		
		then: "the result is true"
			result == true
		and: "the fixFailedAttemptsChecker check method has been invoked 2 times"
			2 * fixedFailedAttemptsChecker.check() >>> [false, true]
	}
	
	def "retry checker returns true in the first retry attempt"() {
		given: "a checker that will return true"
			def trueChecker = Mock BooleanChecker
		and: "a retry checker that will retry at most 3 times (4 times in total) before returning false"
			def retryChecker = new RetryBooleanChecker(trueChecker, 3)
		
		when: "the retry checker is invoked"
		def result = retryChecker.check()
		
		then: "the result is true"
			result == true
		and: "the trueChecker's check method has been invoked 1 time"
			1 * trueChecker.check() >> true
	}
	
	def "retry checker returns false, event if the actual checker would return true in the next attempt"() {
		given: "a checker that will return true in the 3rd attempt"
			def fixedFailedAttemptsChecker = Mock BooleanChecker
		and: "a retry checker that will retry at most 1 times (2 times in total) before returning false"
			def retryChecker = new RetryBooleanChecker(fixedFailedAttemptsChecker, 1)
		
		when: "the retry checker is invoked"
		def result = retryChecker.check()
		
		then: "the result is false"
			result == false
		and: "the fixedFailedAttemptsChecker check method has been invoked 2 times"
			2 * fixedFailedAttemptsChecker.check() >>> [false, false, true]
	}
	
}
