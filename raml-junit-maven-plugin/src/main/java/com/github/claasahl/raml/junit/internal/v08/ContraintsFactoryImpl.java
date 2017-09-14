package com.github.claasahl.raml.junit.internal.v08;

import com.github.claasahl.raml.junit.api.ConstraintsFactory;
import com.github.claasahl.raml.junit.api.RequestConstraints;
import com.github.claasahl.raml.junit.api.ResponseConstraints;
import com.github.claasahl.raml.junit.api.TestCaseKey;

/**
 * The class {@link ContraintsFactoryImpl}.
 * <p/>
 * This is an implementation of the interface {@link ConstraintsFactory} for
 * RAML (v0.8) specifications.
 * 
 * @author Claas Ahlrichs
 *
 */
public class ContraintsFactoryImpl implements ConstraintsFactory {

	@Override
	public RequestConstraints createRequestConstraints(TestCaseKey testCase) {
		return new RequestConstraintsImpl(testCase);
	}

	@Override
	public ResponseConstraints createResponseConstraints(TestCaseKey testCase) {
		return new ResponseConstraintsImpl(testCase);
	}

}
