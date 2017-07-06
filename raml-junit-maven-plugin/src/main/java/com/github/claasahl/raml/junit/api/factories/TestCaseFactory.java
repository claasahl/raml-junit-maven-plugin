package com.github.claasahl.raml.junit.api.factories;

import java.util.List;

import javax.annotation.Nonnull;

import com.github.claasahl.raml.junit.api.TestCaseKey;

public interface TestCaseFactory {
	@Nonnull
	List<TestCaseKey> createTestCases();
}
