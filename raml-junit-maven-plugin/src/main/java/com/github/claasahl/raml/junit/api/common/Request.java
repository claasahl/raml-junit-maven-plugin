package com.github.claasahl.raml.junit.api.common;

import java.util.Collection;

import javax.annotation.Nonnull;

/**
 * The interface {@link Request}.
 * <p/>
 * Implementations of this interface are meant to capture relevant details of
 * HTTP requests for resources in a RAML specification. When sending a request
 * to a particular resource, the corresponding HTTP request is constructed from
 * the details represented by this interface. These include, but are not limited
 * to: headers, cookies, path parameters, content and content type of the body.
 * <p/>
 * This interface provides a customizable hook for representing such details.
 * Thus allowing to easily swap out implementations with more specialized or
 * suitable implementations, if the need arises.
 * <p/>
 * For an overview of related classes, please refer to
 * {@link com.github.claasahl.raml.junit.api.common}.
 * 
 * @see RequestConstraints
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
	 * Returns the (fully-qualified) URL of the request.
	 * 
	 * @return the URL of the request
	 */
	@Nonnull
	String getRequestUrl();

	/**
	 * Returns the request verb of the request. This may be any valid HTTP verb,
	 * but most commonly one of <i>get</i>, <i>post</i>, <i>put</i> or
	 * <i>delete</i>.
	 * 
	 * @return the request verb of the request
	 */
	@Nonnull
	String getRequestVerb();

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
