package com.github.claasahl.raml.junit.api.common;

import java.util.Collection;

import javax.annotation.Nonnull;

/**
 * The interface {@link RequestConstraints}.
 * <p/>
 * Implementations of this interface are meant to capture relevant constraints
 * of HTTP requests for resources in a RAML specification. When sending a
 * request to a particular resource, the corresponding HTTP request must comply
 * with the constraints represented by this interface. These include, but are
 * not limited to: headers, cookies, path parameters, content and content type
 * of the body.
 * <p/>
 * This interface provides a customizable hook for representing such
 * constraints. Thus allowing to easily swap out implementations with more
 * specialized or suitable implementations, if the need arises.
 * <p/>
 * For an overview of related classes, please refer to
 * {@link com.github.claasahl.raml.junit.api.common}.
 * 
 * @see Request
 * 
 * @author Claas Ahlrichs
 *
 */
public interface RequestConstraints {
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
	 * Returns constraints of the <b>query</b> parameters to send with the
	 * request. The parameter's name is expected to be unique within the
	 * returned collection.
	 * <p/>
	 * <b>Note:</b> The returned collection may not contain <code>null</code>
	 * values.
	 * 
	 * @return constraints of the query parameters to send with the request
	 */
	@Nonnull
	Collection<ParameterConstraints> getRequestQueryParameters();

	/**
	 * Returns constraints of the <b>form</b> parameters to send with the
	 * request. The parameter's name is expected to be unique within the
	 * returned collection.
	 * <p/>
	 * <b>Note:</b> The returned collection may not contain <code>null</code>
	 * values.
	 * 
	 * @return constraints of the form parameters to send with the request
	 */
	@Nonnull
	Collection<ParameterConstraints> getRequestFormParameters();

	/**
	 * Returns constraints of the <b>path</b> parameters to send with the
	 * request. The parameter's name is expected to be unique within the
	 * returned collection. The parameters are expected to hold a single value
	 * (i.e. exactly one value).
	 * <p/>
	 * <b>Note:</b> The returned collection may not contain <code>null</code>
	 * values.
	 * 
	 * @return constraints of the path parameters to send with the request
	 */
	@Nonnull
	Collection<ParameterConstraints> getRequestPathParameters();

	/**
	 * Returns constraints of the <b>headers</b> to send with the request. The
	 * header's name is expected to be unique within the returned collection.
	 * <p/>
	 * <b>Note:</b> The returned collection may not contain <code>null</code>
	 * values.
	 * 
	 * @return constraints of the headers to send with the request
	 */
	@Nonnull
	Collection<ParameterConstraints> getRequestHeaders();

	/**
	 * Returns constraints of the <b>cookies</b> to send with the request. The
	 * cookie's name is expected to be unique within the returned collection.
	 * <p/>
	 * <b>Note:</b> The returned collection may not contain <code>null</code>
	 * values.
	 * 
	 * @return constraints of the cookies to send with the request
	 */
	@Nonnull
	Collection<ParameterConstraints> getRequestCookies();

	/**
	 * Returns constraints of the body of the request, if appropriate. In cases
	 * where no body is to be sent with the request, <code>null</code> must be
	 * returned.
	 * 
	 * @return constraints of the body of the request
	 */
	BodyConstraints getRequestBody();

}
