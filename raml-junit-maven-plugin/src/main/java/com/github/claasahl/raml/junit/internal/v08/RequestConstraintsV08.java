package com.github.claasahl.raml.junit.internal.v08;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.raml.v2.api.model.v08.bodies.BodyLike;
import org.raml.v2.api.model.v08.parameters.Parameter;

import com.github.claasahl.raml.junit.api.TestCaseKey;
import com.github.claasahl.raml.junit.api.model.BodyConstraints;
import com.github.claasahl.raml.junit.api.model.ParameterConstraints;
import com.github.claasahl.raml.junit.api.model.RequestConstraints;

public class RequestConstraintsV08 extends ConstraintsBase implements RequestConstraints {

	private final BodyLike body;

	public RequestConstraintsV08(TestCaseKey key) {
		super(key);
		this.body = body(getKey().getContentType(), getMethod().body());
	}

	@Override
	public Collection<ParameterConstraints> getRequestQueryParameters() {
		return ParameterConstraintsFactoryV08.createConstraints(getMethod().queryParameters());
	}

	@Override
	public Collection<ParameterConstraints> getRequestFormParameters() {
		return ParameterConstraintsFactoryV08.createConstraints(new ArrayList<>());
	}

	@Override
	public Collection<ParameterConstraints> getRequestPathParameters() {
		List<Parameter> parameters = new ArrayList<>();
		parameters.addAll(getResource().uriParameters());
		parameters.addAll(getResource().baseUriParameters());
		return ParameterConstraintsFactoryV08.createConstraints(parameters);
	}

	@Override
	public Collection<ParameterConstraints> getRequestHeaders() {
		return ParameterConstraintsFactoryV08.createConstraints(getMethod().headers());
	}

	@Override
	public Collection<ParameterConstraints> getRequestCookies() {
		return ParameterConstraintsFactoryV08.createConstraints(new ArrayList<>());
	}

	@Override
	public BodyConstraints getRequestBody() {
		return BodyConstraintsFactoryV08.createConstraints(this.body);
	}
}
