package com.github.claasahl.raml.junit.internal.v00;

import java.util.ArrayList;
import java.util.Collection;

import com.github.claasahl.raml.junit.api.model.Body;
import com.github.claasahl.raml.junit.api.model.Parameter;
import com.github.claasahl.raml.junit.api.model.Request;

/**
 * The class {@link EmptyRequest}.
 * <p/>
 * This is an implementation of {@link Request} which describes an empty
 * request.
 * 
 * 
 * @author Claas Ahlrichs
 *
 */
public class EmptyRequest implements Request {

	@Override
	public Collection<Parameter> getRequestQueryParameters() {
		return new ArrayList<>();
	}

	@Override
	public Collection<Parameter> getRequestFormParameters() {
		return new ArrayList<>();
	}

	@Override
	public Collection<Parameter> getRequestPathParameters() {
		return new ArrayList<>();
	}

	@Override
	public Collection<Parameter> getRequestHeaders() {
		return new ArrayList<>();
	}

	@Override
	public Collection<Parameter> getRequestCookies() {
		return new ArrayList<>();
	}

	@Override
	public Body getRequestBody() {
		return null;
	}

}
