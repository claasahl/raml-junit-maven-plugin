package com.github.claasahl.raml.junit.api.common;

import java.util.Collection;

import javax.annotation.Nonnull;

/**
 * The interface {@link Response}.
 * <p/>
 * Implementations of this interface are meant to capture relevant details of
 * HTTP responses from resources in a RAML specification. When receiving a
 * response from a particular resource, the corresponding HTTP response's
 * details are represented by this interface. These details include, but are not
 * limited to: headers, cookies, content and content type of the body.
 * <p/>
 * This interface provides a customizable hook for representing such details.
 * Thus allowing easy replacement of implementations with more specialized or
 * suitable implementations, if the need arises.
 * <p/>
 * For an overview of related classes, please refer to
 * {@link com.github.claasahl.raml.junit.api.common}.
 * 
 * @see ResponseConstraints
 * 
 * @author Claas Ahlrichs
 *
 */
public interface Response {
	// TODO implement sanity check that verifies values for parameters (make
	// sure that they are valid)
	// TODO implement sanity check that verifies the body (i.e. make sure that
	// it is valid)

	/**
	 * Returns the response code received with the response.
	 * 
	 * @return the response code received with the response
	 */
	@Nonnull
	String getResponseCode();

	/**
	 * Returns the <b>headers</b> received with the response. The header's name
	 * is expected to be unique within the returned collection.
	 * <p/>
	 * <b>Note:</b> The returned collection may not contain <code>null</code>
	 * values.
	 * 
	 * @return the headers received with the response
	 */
	@Nonnull
	Collection<Parameter> getResponseHeaders();

	/**
	 * Returns the <b>cookies</b> received with the response. The cookie's name
	 * is expected to be unique within the returned collection.
	 * <p/>
	 * <b>Note:</b> The returned collection may not contain <code>null</code>
	 * values.
	 * 
	 * @return the cookies received with the response
	 */
	@Nonnull
	Collection<Parameter> getResponseCookies();

	/**
	 * Returns the body of the response, if appropriate. In cases where no body
	 * is to be received with the response, <code>null</code> must be returned.
	 * 
	 * @return the body of the response
	 */
	Body getResponseBody();
}
