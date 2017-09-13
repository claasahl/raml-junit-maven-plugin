package com.github.claasahl.raml.junit.api;

import java.util.Collection;

import javax.annotation.Nonnull;

/**
 * The interface {@link ResponseConstraints}.
 * 
 * @author Claas Ahlrichs
 *
 */
public interface ResponseConstraints {
	/**
	 * Returns the response code received with the response.
	 * 
	 * @return the response code received with the response
	 */
	@Nonnull
	String getResponseCode();

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
