package com.github.claasahl.raml.junit.internal.v00;

import com.github.claasahl.raml.junit.api.TestCaseKey;
import com.github.claasahl.raml.junit.api.factories.RequestFactory;
import com.github.claasahl.raml.junit.api.model.Request;

/**
 * The class {@link EmptyRequestFactory}.
 * <p/>
 * This is an implementation of the {@link RequestFactory} which creates empty
 * requests.
 * 
 * @author Claas Ahlrichs
 *
 */
public class EmptyRequestFactory implements RequestFactory {

	@Override
	public Request createRequest(TestCaseKey testCase) {
		return new EmptyRequest();
	}

}
