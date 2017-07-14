package com.github.claasahl.raml.junit.internal.v00;

import java.util.ArrayList;
import java.util.Collection;

import com.github.claasahl.raml.junit.api.model.BodyConstraints;
import com.github.claasahl.raml.junit.api.model.ParameterConstraints;
import com.github.claasahl.raml.junit.api.model.ResponseConstraints;

/**
 * The class {@link EmptyResponseContraints}.
 * <p/>
 * This is an implementation of {@link ResponseConstraints} which describes an
 * unconstrained response.
 * 
 * @author Claas Ahlrichs
 *
 */
public class EmptyResponseContraints implements ResponseConstraints {

	@Override
	public Collection<ParameterConstraints> getResponseHeaders() {
		return new ArrayList<>();
	}

	@Override
	public Collection<ParameterConstraints> getResponseCookies() {
		return new ArrayList<>();
	}

	@Override
	public BodyConstraints getResponseBody() {
		return null;
	}

}
