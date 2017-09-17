package com.github.claasahl.raml.junit.api.common;

import javax.annotation.Nonnull;

/**
 * The interface {@link RequestFactory}.
 * <p/>
 * This interface provides a customizable hook for generating {@link Request}s.
 * Thus allowing to easily replace the default strategy with a more specialized,
 * suitable strategy, if the need arises.
 * <p/>
 * Implementing classes must provide a zero-argument constructor.
 * 
 * @author Claas Ahlrichs
 *
 */
public interface RequestFactory {
	/**
	 * Returns the request for the test case.
	 * 
	 * @param testCase
	 *            the test case
	 * @return the request for the test case
	 */
	@Nonnull
	Request createRequest(TestCaseKey testCase);
}
