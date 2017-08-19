package com.github.claasahl.raml.junit.internal.v08;

import java.util.ArrayList;
import java.util.Collection;

import com.github.claasahl.raml.junit.api.TestCaseKey;
import com.github.claasahl.raml.junit.api.model.BodyConstraints;
import com.github.claasahl.raml.junit.api.model.ParameterConstraints;
import com.github.claasahl.raml.junit.api.model.ResponseConstraints;

public class ResponseConstraintsV08 extends ConstraintsBase implements ResponseConstraints {

	public ResponseConstraintsV08(TestCaseKey key) {
		super(key);
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
		// TODO Auto-generated method stub
		return null;
	}

}
