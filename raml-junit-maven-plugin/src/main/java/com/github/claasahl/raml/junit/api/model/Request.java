package com.github.claasahl.raml.junit.api.model;

import java.util.Collection;

import javax.annotation.Nonnull;

/**
 * The interface {@link Request}.
 * 
 * @author Claas Ahlrichs
 *
 */
public interface Request {
	// TODO implement sanity check that verifies values for parameters (make
	// sure that they are valid)
	// TODO implement sanity check that verifies the body (i.e. make sure that
	// it is valid)

	/**
	 * Returns the <b>query</b> parameters to send with the request. The
	 * parameter's name is expected to be unique within the returned collection.
	 * <p/>
	 * <b>Note:</b> The returned collection may not contain <code>null</code>
	 * values.
	 * 
	 * @return the query parameters to send with the request
	 */
	@Nonnull
	Collection<Parameter> getRequestQueryParameters();

	/**
	 * Returns the <b>form</b> parameters to send with the request. The
	 * parameter's name is expected to be unique within the returned collection.
	 * <p/>
	 * <b>Note:</b> The returned collection may not contain <code>null</code>
	 * values.
	 * 
	 * @return the form parameters to send with the request
	 */
	@Nonnull
	Collection<Parameter> getRequestFormParameters();

	/**
	 * Returns the <b>path</b> parameters to send with the request. The
	 * parameter's name is expected to be unique within the returned collection.
	 * The parameters are expected to hold a single value (i.e. exactly one
	 * value).
	 * <p/>
	 * <b>Note:</b> The returned collection may not contain <code>null</code>
	 * values.
	 * 
	 * @return the path parameters to send with the request
	 */
	@Nonnull
	Collection<Parameter> getRequestPathParameters();

	/**
	 * Returns the <b>headers</b> to send with the request. The header's name is
	 * expected to be unique within the returned collection.
	 * <p/>
	 * <b>Note:</b> The returned collection may not contain <code>null</code>
	 * values.
	 * 
	 * @return the headers to send with the request
	 */
	@Nonnull
	Collection<Parameter> getRequestHeaders();

	/**
	 * Returns the <b>cookies</b> to send with the request. The cookie's name is
	 * expected to be unique within the returned collection.
	 * <p/>
	 * <b>Note:</b> The returned collection may not contain <code>null</code>
	 * values.
	 * 
	 * @return the cookies to send with the request
	 */
	@Nonnull
	Collection<Parameter> getRequestCookies();

	/**
	 * Returns the body of the request, if appropriate. In cases where no body
	 * is to be sent with the request, <code>null</code> must be returned.
	 * 
	 * @return the body of the request
	 */
	Body getRequestBody();
}
