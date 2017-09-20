package com.github.claasahl.raml.junit.api.common;

import javax.annotation.Nonnull;

/**
 * The interface {@link ConstraintsFactory}.
 * <p/>
 * This interface provides a customizable hook for generating constraints. Thus
 * allowing to easily replace the default strategy with a more specialized,
 * suitable strategy, if the need arises.
 * <p/>
 * Implementations of interface feed on the previously extracted test cases (see
 * {@link TestCaseFactory}) and look up details about a particular test case.
 * For each test case, implementations are meant to provide a set of constraints
 * to which an HTTP request and its HTTP response must adhere.
 * <p/>
 * For an overview of related classes, please refer to
 * {@link com.github.claasahl.raml.junit.api.common}.
 * <p/>
 * Implementing classes must provide a zero-argument constructor.
 * 
 * @author Claas Ahlrichs
 *
 */
public interface ConstraintsFactory {
	/**
	 * Returns the constraints of the request for the test case.
	 * 
	 * @param testCase
	 *            the test case
	 * @return the constraints of the request for the test case
	 */
	@Nonnull
	RequestConstraints createRequestConstraints(TestCaseKey testCase);

	/**
	 * Returns the constraints of the response for the test case.
	 * 
	 * @param testCase
	 *            the test case
	 * @return the constraints of the response for the test case
	 */
	@Nonnull
	ResponseConstraints createResponseConstraints(TestCaseKey testCase);
}
