package com.github.claasahl.raml.v08.junit;

import java.util.Collection;

import javax.annotation.Nonnull;

/**
 * The interface {@link ResourceResponse}.
 * 
 * @author Claas Ahlrichs
 *
 */
public interface ResourceResponse {
	// TODO implement sanity check for null-values in parameter collection
	// TODO implement sanity check that verifies values for parameters (make sure that they are valid)
	// TODO implement sanity check that verifies the body (i.e. make sure that it is valid)

	/**
	 * Returns the (expected) <b>header</b> names and their values to receive
	 * with the response. The header's name is expected to be unique within the
	 * returned collection.
	 * <p/>
	 * <b>Note:</b> The returned collection may not contain <code>null</code>
	 * values.
	 * 
	 * @return the header names and their values to receive with the response
	 */
	@Nonnull
	Collection<Parameter> getHeaders();

	/**
	 * Returns the (expected) <b>cookie</b> names and their values to receive
	 * with the response. The cookie's name is expected to be unique within the
	 * returned collection.
	 * <p/>
	 * <b>Note:</b> The returned collection may not contain <code>null</code>
	 * values.
	 * 
	 * @return the cookie names and their values to receive with the response
	 */
	@Nonnull
	Collection<Parameter> getCookies();
	
	/**
	 * Returns the (expected) status code of the response.
	 * 
	 * @return the status code of the response
	 */
	@Nonnull
	String getStatusCode();

	/**
	 * Returns the (expected) body of the response, if appropriate. In cases
	 * where no body is expected with the response, <code>null</code> must be
	 * returned.
	 * 
	 * @return the body of the response
	 */
	Body getBody();
}
