package com.github.claasahl.raml.junit.internal.v08;

import java.util.ArrayList;
import java.util.Collection;

import org.raml.v2.api.model.v08.bodies.BodyLike;

import com.github.claasahl.raml.junit.api.TestCaseKey;
import com.github.claasahl.raml.junit.api.model.BodyConstraints;
import com.github.claasahl.raml.junit.api.model.ParameterConstraints;
import com.github.claasahl.raml.junit.api.model.ResponseConstraints;

public class ResponseConstraintsV08 extends ConstraintsBase implements ResponseConstraints {

	private final BodyLike body;

	public ResponseConstraintsV08(TestCaseKey key) {
		super(key);
		this.body = body(getKey().getContentType(), getResponse().body());
	}
	
	@Override
	public String getResponseCode() {
		return getKey().getResponseCode();
	}

	@Override
	public Collection<ParameterConstraints> getResponseHeaders() {
		return ParameterConstraintsFactoryV08.createConstraints(getResponse().headers());
	}

	@Override
	public Collection<ParameterConstraints> getResponseCookies() {
		return ParameterConstraintsFactoryV08.createConstraints(new ArrayList<>());
	}

	@Override
	public BodyConstraints getResponseBody() {
		return this.body == null ? null : BodyConstraintsFactoryV08.createConstraints(this.body);
	}
}
