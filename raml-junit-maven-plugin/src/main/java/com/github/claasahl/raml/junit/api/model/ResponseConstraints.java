package com.github.claasahl.raml.junit.api.model;

import java.util.Collection;

import javax.annotation.Nonnull;

import com.github.claasahl.raml.v08.junit.BodyConstraints;
import com.github.claasahl.raml.v08.junit.ParameterConstraints;

/**
 * The interface {@link ResponseConstraints}.
 * 
 * @author Claas Ahlrichs
 *
 */
public interface ResponseConstraints {
	// TODO implement sanity check for duplicate parameter names
	// TODO implement sanity check for null-values in parameter collection
	// TODO implement sanity check that verifies values for parameters (make
	// sure that they are valid)
	// TODO implement sanity check that verifies the body (i.e. make sure that
	// it is valid)

	/**
	 * Returns constraints of the <b>headers</b> to receive with the response.
	 * The header's name is expected to be unique within the returned
	 * collection.
	 * <p/>
	 * <b>Note:</b> The returned collection may not contain <code>null</code>
	 * values.
	 * 
	 * @return constraints of the headers to receive with the response
	 */
	@Nonnull
	Collection<ParameterConstraints> getResponseHeaders();

	/**
	 * Returns constraints of the <b>cookies</b> to receive with the response.
	 * The cookie's name is expected to be unique within the returned
	 * collection.
	 * <p/>
	 * <b>Note:</b> The returned collection may not contain <code>null</code>
	 * values.
	 * 
	 * @return constraints of the cookies to receive with the response
	 */
	@Nonnull
	Collection<ParameterConstraints> getResponseCookies();

	/**
	 * Returns constraints of the body of the response, if appropriate. In cases
	 * where no body is expected with the response, <code>null</code> must be
	 * returned.
	 * 
	 * @return constraints of the body of the response
	 */
	BodyConstraints getResponseBody();
}
