package com.github.claasahl.raml.junit.internal.v08;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.raml.v2.api.model.v08.bodies.BodyLike;
import org.raml.v2.api.model.v08.parameters.Parameter;

import com.github.claasahl.raml.junit.api.BodyConstraints;
import com.github.claasahl.raml.junit.api.ParameterConstraints;
import com.github.claasahl.raml.junit.api.RequestConstraints;
import com.github.claasahl.raml.junit.api.TestCaseKey;

public class RequestConstraintsImpl extends Base implements RequestConstraints {

	public RequestConstraintsImpl(TestCaseKey key) {
		super(key);
	}

	@Override
	public String getRequestUrl() {
		return getKey().getRequestUrl();
	}

	@Override
	public String getRequestVerb() {
		return getKey().getRequestVerb();
	}

	@Override
	public Collection<ParameterConstraints> getRequestQueryParameters() {
		return ParameterConstraintsFactoryImpl.createConstraints(getMethod().queryParameters());
	}

	@Override
	public Collection<ParameterConstraints> getRequestFormParameters() {
		return ParameterConstraintsFactoryImpl.createConstraints(new ArrayList<>());
	}

	@Override
	public Collection<ParameterConstraints> getRequestPathParameters() {
		List<Parameter> parameters = new ArrayList<>();
		parameters.addAll(getResource().uriParameters());
		parameters.addAll(getResource().baseUriParameters());
		return ParameterConstraintsFactoryImpl.createConstraints(parameters);
	}

	@Override
	public Collection<ParameterConstraints> getRequestHeaders() {
		return ParameterConstraintsFactoryImpl.createConstraints(getMethod().headers());
	}

	@Override
	public Collection<ParameterConstraints> getRequestCookies() {
		return ParameterConstraintsFactoryImpl.createConstraints(new ArrayList<>());
	}

	@Override
	public BodyConstraints getRequestBody() {
		BodyLike body = getBody(true);
		return body == null ? null : BodyConstraintsFactoryImpl.createConstraints(body);
	}
}
