package com.github.claasahl.raml.junit.internal.v00;

import com.github.claasahl.raml.junit.api.TestCaseKey;
import com.github.claasahl.raml.junit.api.factories.ConstraintsFactory;
import com.github.claasahl.raml.junit.api.model.RequestConstraints;
import com.github.claasahl.raml.junit.api.model.ResponseConstraints;

/**
 * The class {@link EmptyContraintsFactory}.
 * <p/>
 * This is an implementation of the {@link ConstraintsFactory} which creates
 * unconstrained requests and responses.
 * 
 * @author Claas Ahlrichs
 *
 */
public class EmptyContraintsFactory implements ConstraintsFactory {

	@Override
	public RequestConstraints createRequestConstraints(TestCaseKey testCase) {
		return new EmptyRequestContraints();
	}

	@Override
	public ResponseConstraints createResponseConstraints(TestCaseKey testCase) {
		return new EmptyResponseContraints();
	}

}
