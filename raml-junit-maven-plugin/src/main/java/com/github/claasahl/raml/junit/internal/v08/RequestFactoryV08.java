package com.github.claasahl.raml.junit.internal.v08;

import com.github.claasahl.raml.junit.api.TestCaseKey;
import com.github.claasahl.raml.junit.api.factories.RequestFactory;
import com.github.claasahl.raml.junit.api.model.Request;

public class RequestFactoryV08 implements RequestFactory {

	@Override
	public Request createRequest(TestCaseKey testCase) {
		return new RequestV08(testCase);
	}

}
