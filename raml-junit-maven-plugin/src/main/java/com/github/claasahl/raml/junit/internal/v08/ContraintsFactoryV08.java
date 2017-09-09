package com.github.claasahl.raml.junit.internal.v08;

import com.github.claasahl.raml.junit.api.ConstraintsFactory;
import com.github.claasahl.raml.junit.api.TestCaseKey;
import com.github.claasahl.raml.junit.api.model.RequestConstraints;
import com.github.claasahl.raml.junit.api.model.ResponseConstraints;

public class ContraintsFactoryV08 implements ConstraintsFactory{

	@Override
	public RequestConstraints createRequestConstraints(TestCaseKey testCase) {
		return new RequestConstraintsV08(testCase);
	}

	@Override
	public ResponseConstraints createResponseConstraints(TestCaseKey testCase) {
		return new ResponseConstraintsV08(testCase);
	}
	
}
