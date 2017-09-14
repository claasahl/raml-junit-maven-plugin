package com.github.claasahl.raml.junit.internal.v08;

import java.util.ArrayList;
import java.util.Collection;

import org.raml.v2.api.model.v08.bodies.BodyLike;

import com.github.claasahl.raml.junit.api.BodyConstraints;
import com.github.claasahl.raml.junit.api.ParameterConstraints;
import com.github.claasahl.raml.junit.api.ResponseConstraints;
import com.github.claasahl.raml.junit.api.TestCaseKey;

public class ResponseConstraintsImpl extends Base implements ResponseConstraints {

	public ResponseConstraintsImpl(TestCaseKey key) {
		super(key);
	}

	@Override
	public String getResponseCode() {
		return getKey().getResponseCode();
	}

	@Override
	public Collection<ParameterConstraints> getResponseHeaders() {
		return ParameterConstraintsFactoryImpl.createConstraints(getResponse().headers());
	}

	@Override
	public Collection<ParameterConstraints> getResponseCookies() {
		return ParameterConstraintsFactoryImpl.createConstraints(new ArrayList<>());
	}

	@Override
	public BodyConstraints getResponseBody() {
		BodyLike body = getBody(false);
		return body == null ? null : BodyConstraintsFactoryImpl.createConstraints(body);
	}
}
