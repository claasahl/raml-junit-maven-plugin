package com.github.claasahl.raml.v08.junit;

import java.util.Collection;

import javax.annotation.Nonnull;

/**
 * The interface {@link ResourceRequest}.
 * 
 * @author Claas Ahlrichs
 *
 */
public interface ResourceRequest {
	// TODO separate validation constraints (i.e. details from RAML) from the part that actually picks concrete values (i.e. the latter should be easily "swapable")
	// TODO implement sanity check for duplicate parameter names
	// TODO implement sanity check for null-values in parameter collection
	// TODO implement sanity check for multiple values (see path parameters)
	// TODO implement sanity check that verifies values for parameters (make sure that they are valid)
	// TODO implement sanity check that verifies the body (i.e. make sure that it is valid)

	/**
	 * Returns the <b>query</b> parameter names and their values to send with
	 * the request. The parameter's name is expected to be unique within the
	 * returned collection.
	 * <p/>
	 * <b>Note:</b> The returned collection may not contain <code>null</code>
	 * values.
	 * 
	 * @return the query parameter names and their values to send with the
	 *         request
	 */
	@Nonnull
	Collection<Parameter> getRequestQueryParameters();

	/**
	 * Returns the <b>form</b> parameter names and their values to send with the
	 * request. The parameter's name is expected to be unique within the
	 * returned collection.
	 * <p/>
	 * <b>Note:</b> The returned collection may not contain <code>null</code>
	 * values.
	 * 
	 * @return the form parameter names and their values to send with the
	 *         request
	 */
	@Nonnull
	Collection<Parameter> getRequestFormParameters();

	/**
	 * Returns the <b>path</b> parameter names and their values to send with the
	 * request. The parameter's name is expected to be unique within the
	 * returned collection. The parameters are expected to hold a single value
	 * (i.e. exactly one value).
	 * <p/>
	 * <b>Note:</b> The returned collection may not contain <code>null</code>
	 * values.
	 * 
	 * @return the path parameter names and their values to send with the
	 *         request
	 */
	@Nonnull
	Collection<Parameter> getRequestPathParameters();

	/**
	 * Returns the <b>header</b> names and their values to send with the
	 * request. The header's name is expected to be unique within the returned
	 * collection.
	 * <p/>
	 * <b>Note:</b> The returned collection may not contain <code>null</code>
	 * values.
	 * 
	 * @return the header names and their values to send with the request
	 */
	@Nonnull
	Collection<Parameter> getRequestHeaders();

	/**
	 * Returns the <b>cookie</b> names and their values to send with the
	 * request. The cookie's name is expected to be unique within the returned
	 * collection.
	 * <p/>
	 * <b>Note:</b> The returned collection may not contain <code>null</code>
	 * values.
	 * 
	 * @return the cookie names and their values to send with the request
	 */
	@Nonnull
	Collection<Parameter> getRequestCookies();

	/**
	 * Returns the HTTP verb of the request. This may be any valid HTTP verb,
	 * but most commonly one of <i>get</i>, <i>post</i>, <i>put</i> or
	 * <i>delete</i>.
	 * 
	 * @return the HTTP verb of the request
	 */
	@Nonnull
	String getRequestVerb();

	/**
	 * Returns the (fully-qualified) URL of the request.
	 * 
	 * @return the URL of the request
	 */
	@Nonnull
	String getRequestUrl();

	/**
	 * Returns the body of the request, if appropriate. In cases where no body
	 * is to be sent with the request, <code>null</code> must be returned.
	 * 
	 * @return the body of the request
	 */
	Body getRequestBody();
	
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
	Collection<Parameter> getResponseHeaders();

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
	Collection<Parameter> getResponseCookies();
	
	/**
	 * Returns the (expected) status code of the response.
	 * 
	 * @return the status code of the response
	 */
	@Nonnull
	String getResponseStatusCode();

	/**
	 * Returns the (expected) body of the response, if appropriate. In cases
	 * where no body is expected with the response, <code>null</code> must be
	 * returned.
	 * 
	 * @return the body of the response
	 */
	Body getResponseBody();
}
