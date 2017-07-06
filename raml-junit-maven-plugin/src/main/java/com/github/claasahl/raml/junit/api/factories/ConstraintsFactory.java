package com.github.claasahl.raml.junit.api.factories;

import javax.annotation.Nonnull;

import com.github.claasahl.raml.junit.api.TestCaseKey;
import com.github.claasahl.raml.junit.api.model.RequestConstraints;
import com.github.claasahl.raml.junit.api.model.ResponseConstraints;

public interface ConstraintsFactory {
	@Nonnull
	RequestConstraints createRequestConstraints(TestCaseKey testCase);
	
	@Nonnull
	ResponseConstraints createResponseConstraints(TestCaseKey testCase);
}
