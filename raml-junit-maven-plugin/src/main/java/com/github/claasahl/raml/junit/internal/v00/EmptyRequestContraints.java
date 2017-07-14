package com.github.claasahl.raml.junit.internal.v00;

import java.util.ArrayList;
import java.util.Collection;

import com.github.claasahl.raml.junit.api.model.BodyConstraints;
import com.github.claasahl.raml.junit.api.model.ParameterConstraints;
import com.github.claasahl.raml.junit.api.model.RequestConstraints;

/**
 * The class {@link EmptyRequestContraints}.
 * <p/>
 * This is an implementation of {@link RequestConstraints} which describes an
 * unconstrained request.
 * 
 * @author Claas Ahlrichs
 *
 */
public class EmptyRequestContraints implements RequestConstraints {

	@Override
	public Collection<ParameterConstraints> getRequestQueryParameters() {
		return new ArrayList<>();
	}

	@Override
	public Collection<ParameterConstraints> getRequestFormParameters() {
		return new ArrayList<>();
	}

	@Override
	public Collection<ParameterConstraints> getRequestPathParameters() {
		return new ArrayList<>();
	}

	@Override
	public Collection<ParameterConstraints> getRequestHeaders() {
		return new ArrayList<>();
	}

	@Override
	public Collection<ParameterConstraints> getRequestCookies() {
		return new ArrayList<>();
	}

	@Override
	public BodyConstraints getRequestBody() {
		return null;
	}

}
