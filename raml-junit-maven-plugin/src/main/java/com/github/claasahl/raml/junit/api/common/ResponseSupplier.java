package com.github.claasahl.raml.junit.api.common;

import javax.annotation.Nonnull;

/**
 * The interface {@link ResponseSupplier}.
 * <p/>
 * This interface provides a customizable hook for generating {@link Response}s.
 * Thus allowing easy replacement of the default strategy with a more
 * specialized, suitable strategy, if the need arises.
 * <p/>
 * Implementations of this interface take previously generated requests (see
 * {@link RequestFactory}), perform the actual HTTP request and provide the
 * API's response. Naturally, these HTTP responses must adhere to previously
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
public interface ResponseSupplier {
	/**
	 * Returns the response for the request.
	 * 
	 * @param request
	 *            the request
	 * @return the response for the request
	 */
	@Nonnull
	Response getResponse(Request request);
}
