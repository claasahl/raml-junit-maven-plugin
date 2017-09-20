package com.github.claasahl.raml.junit.api.common;

import javax.annotation.Nonnull;

/**
 * The interface {@link RequestFactory}.
 * <p/>
 * This interface provides a customizable hook for generating {@link Request}s.
 * Thus allowing to easily replace the default strategy with a more specialized,
 * suitable strategy, if the need arises.
 * <p/>
 * Implementations of this interface are meant to produce HTTP requests for a
 * particular test case. These HTTP requests may be based on the RAML document
 * itself (e.g. default values or example values), local JSON / XML files, or
 * other sources. Naturally, these HTTP requests must also adhere to previously
 * extracted constraints (see {@link ConstraintsFactory}).
 * <p/>
 * For an overview of related classes, please refer to
 * {@link com.github.claasahl.raml.junit.api.common}.
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
