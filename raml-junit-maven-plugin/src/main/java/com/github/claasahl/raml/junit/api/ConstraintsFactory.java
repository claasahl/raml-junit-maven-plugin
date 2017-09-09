package com.github.claasahl.raml.junit.api;

import javax.annotation.Nonnull;

import com.github.claasahl.raml.junit.api.model.RequestConstraints;
import com.github.claasahl.raml.junit.api.model.ResponseConstraints;

/**
 * The interface {@link ConstraintsFactory}.
 * <p/>
 * This interface provides a customizable hook for generating constraints. Thus
 * allowing to easily replace the default strategy with a more specialized,
 * suitable strategy, if the need arises.
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
