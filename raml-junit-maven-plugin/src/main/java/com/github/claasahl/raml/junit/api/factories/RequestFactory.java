package com.github.claasahl.raml.junit.api.factories;

import javax.annotation.Nonnull;

import com.github.claasahl.raml.junit.api.TestCaseKey;
import com.github.claasahl.raml.junit.api.model.Request;

public interface RequestFactory {
	@Nonnull
	Request createRequest(TestCaseKey testCase);
}
