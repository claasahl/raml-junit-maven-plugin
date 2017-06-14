package com.github.claasahl.raml.v08.junit;

import java.util.Collection;

public interface ResourceRequest {
	// TODO implement sanity check for duplicate parameter names
	// TODO implement sanity check for null-values in parameter collection
	// TODO implement sanity check for multiple values (see path parameters)
	
	/**
	 * Returns the <b>query</b> parameter names and their values to send with
	 * the request. The parameter's name is expected to be unique within the
	 * returned collection.
	 * <p/>
	 * <b>Note:</b> The returned collection may not be <code>null</code> nor may
	 * it contain <code>null</code> values.
	 * 
	 * @return the query parameter names and their values to send with the
	 *         request
	 */
	Collection<Parameter> getQueryParameters();

	/**
	 * Returns the <b>form</b> parameter names and their values to send with the
	 * request. The parameter's name is expected to be unique within the
	 * returned collection.
	 * <p/>
	 * <b>Note:</b> The returned collection may not be <code>null</code> nor may
	 * it contain <code>null</code> values.
	 * 
	 * @return the form parameter names and their values to send with the
	 *         request
	 */
	Collection<Parameter> getFormParameters();

	/**
	 * Returns the <b>path</b> parameter names and their values to send with the
	 * request. The parameter's name is expected to be unique within the
	 * returned collection. The parameters are expected to hold a single value
	 * (i.e. exactly one value).
	 * <p/>
	 * <b>Note:</b> The returned collection may not be <code>null</code> nor may
	 * it contain <code>null</code> values.
	 * 
	 * @return the path parameter names and their values to send with the
	 *         request
	 */
	Collection<Parameter> getPathParameters();

	/**
	 * Returns the <b>header</b> names and their values to send with the
	 * request. The header's name is expected to be unique within the returned
	 * collection.
	 * <p/>
	 * <b>Note:</b> The returned collection may not be <code>null</code> nor may
	 * it contain <code>null</code> values.
	 * 
	 * @return the header names and their values to send with the request
	 */
	Collection<Parameter> getHeaders();

	/**
	 * Returns the <b>cookie</b> names and their values to send with the
	 * request. The cookie's name is expected to be unique within the returned
	 * collection.
	 * <p/>
	 * <b>Note:</b> The returned collection may not be <code>null</code> nor may
	 * it contain <code>null</code> values.
	 * 
	 * @return the cookie names and their values to send with the request
	 */
	Collection<Parameter> getCookies();

	String getVerb();

	String getPath();

	/**
	 * Returns the content type to send with the request.
	 * @return the content type to send with the request
	 */
	String getContentType();

	String getBody();
}
