package com.github.claasahl.raml.junit.internal.v08;

import com.github.claasahl.raml.junit.api.common.Request;
import com.github.claasahl.raml.junit.api.common.RequestFactory;
import com.github.claasahl.raml.junit.api.common.TestCaseKey;

/**
 * The class {@link RequestFactoryImpl}.
 * <p/>
 * This is an implementation of the interface {@link RequestFactory} for RAML
 * (v0.8) specifications.
 * 
 * @author Claas Ahlrichs
 *
 */
public class RequestFactoryImpl implements RequestFactory {

	@Override
	public Request createRequest(TestCaseKey testCase) {
		return new RequestImpl(testCase);
	}

}
