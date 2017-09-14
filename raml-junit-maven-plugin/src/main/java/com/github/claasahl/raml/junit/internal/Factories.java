package com.github.claasahl.raml.junit.internal;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.claasahl.raml.junit.api.ConstraintsFactory;
import com.github.claasahl.raml.junit.api.Request;
import com.github.claasahl.raml.junit.api.RequestConstraints;
import com.github.claasahl.raml.junit.api.RequestFactory;
import com.github.claasahl.raml.junit.api.ResponseConstraints;
import com.github.claasahl.raml.junit.api.TestCaseFactory;
import com.github.claasahl.raml.junit.api.TestCaseKey;

public final class Factories implements TestCaseFactory, ConstraintsFactory, RequestFactory {

	private static final String TEST_CASE_FACTORY = "raml.junit.test_case_factory.";
	private static final String CONSTRAINTS_FACTORY = "raml.junit.constraints_factory.";
	private static final String REQUEST_FACTORY = "raml.junit.request_factory.";
	private static final Map<String, Factories> instances = new HashMap<>();
	private final TestCaseFactory testCaseFactory;
	private final ConstraintsFactory constraintsFactory;
	private final RequestFactory requestFactory;

	private Factories(TestCaseFactory testCaseFactory, ConstraintsFactory constraintsFactory,
			RequestFactory requestFactory) {
		this.testCaseFactory = testCaseFactory;
		this.constraintsFactory = constraintsFactory;
		this.requestFactory = requestFactory;
	}

	@Override
	public Request createRequest(TestCaseKey testCase) {
		return this.requestFactory.createRequest(testCase);
	}

	@Override
	public RequestConstraints createRequestConstraints(TestCaseKey testCase) {
		return this.constraintsFactory.createRequestConstraints(testCase);
	}

	@Override
	public ResponseConstraints createResponseConstraints(TestCaseKey testCase) {
		return this.constraintsFactory.createResponseConstraints(testCase);
	}

	@Override
	public List<TestCaseKey> createTestCases(URL ramlUrl) {
		return this.testCaseFactory.createTestCases(ramlUrl);
	}

	public static Factories getFactories(String ramlVersion) {
		if (!instances.containsKey(ramlVersion)) {
			instances.put(ramlVersion, createFactory(ramlVersion));
		}
		return instances.get(ramlVersion);
	}

	private static Factories createFactory(String ramlVersion) {
		TestCaseFactory testCaseFactory = Utils.createFactory(TEST_CASE_FACTORY + ramlVersion);
		ConstraintsFactory constraintsFactory = Utils.createFactory(CONSTRAINTS_FACTORY + ramlVersion);
		RequestFactory requestFactory = Utils.createFactory(REQUEST_FACTORY + ramlVersion);
		return new Factories(testCaseFactory, constraintsFactory, requestFactory);
	}
}
