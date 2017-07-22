package com.github.claasahl.raml.junit.api.model;

import java.util.Collection;

import javax.annotation.Nonnull;

/**
 * The interface {@link RequestConstraints}.
 * 
 * @author Claas Ahlrichs
 *
 */
public interface RequestConstraints {
	// TODO implement sanity check that verifies values for parameters (make
	// sure that they are valid)
	// TODO implement sanity check that verifies the body (i.e. make sure that
	// it is valid)

	/**
	 * Returns constraints of the <b>query</b> parameters to send with the request. The
	 * parameter's name is expected to be unique within the returned collection.
	 * <p/>
	 * <b>Note:</b> The returned collection may not contain <code>null</code>
	 * values.
	 * 
	 * @return constraints of the query parameters to send with the request
	 */
	@Nonnull
	Collection<ParameterConstraints> getRequestQueryParameters();

	/**
	 * Returns constraints of the <b>form</b> parameters to send with the request. The
	 * parameter's name is expected to be unique within the returned collection.
	 * <p/>
	 * <b>Note:</b> The returned collection may not contain <code>null</code>
	 * values.
	 * 
	 * @return constraints of the form parameters to send with the request
	 */
	@Nonnull
	Collection<ParameterConstraints> getRequestFormParameters();

	/**
	 * Returns constraints of the <b>path</b> parameters to send with the request. The
	 * parameter's name is expected to be unique within the returned collection.
	 * The parameters are expected to hold a single value (i.e. exactly one
	 * value).
	 * <p/>
	 * <b>Note:</b> The returned collection may not contain <code>null</code>
	 * values.
	 * 
	 * @return constraints of the path parameters to send with the request
	 */
	@Nonnull
	Collection<ParameterConstraints> getRequestPathParameters();

	/**
	 * Returns constraints of the <b>headers</b> to send with the request. The header's
	 * name is expected to be unique within the returned collection.
	 * <p/>
	 * <b>Note:</b> The returned collection may not contain <code>null</code>
	 * values.
	 * 
	 * @return constraints of the headers to send with the request
	 */
	@Nonnull
	Collection<ParameterConstraints> getRequestHeaders();

	/**
	 * Returns constraints of the <b>cookies</b> to send with the request. The cookie's
	 * name is expected to be unique within the returned collection.
	 * <p/>
	 * <b>Note:</b> The returned collection may not contain <code>null</code>
	 * values.
	 * 
	 * @return constraints of the cookies to send with the request
	 */
	@Nonnull
	Collection<ParameterConstraints> getRequestCookies();

	/**
	 * Returns constraints of the body of the request, if appropriate. In cases where no body
	 * is to be sent with the request, <code>null</code> must be returned.
	 * 
	 * @return constraints of the body of the request
	 */
	BodyConstraints getRequestBody();

}
