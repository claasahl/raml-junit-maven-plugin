package com.github.claasahl.raml.junit.api;

import javax.annotation.Nonnull;

/**
 * The interface {@link ResponseSupplier}.
 * <p/>
 * This interface provides a customizable hook for generating {@link Response}s.
 * Thus allowing to easily replace the default strategy with a more specialized,
 * suitable strategy, if the need arises.
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
