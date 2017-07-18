package com.github.claasahl.raml.junit.internal;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import com.github.claasahl.raml.junit.api.TestCaseKey;
import com.github.claasahl.raml.junit.api.factories.ConstraintsFactory;
import com.github.claasahl.raml.junit.api.factories.RamlUrlFactory;
import com.github.claasahl.raml.junit.api.factories.RequestFactory;
import com.github.claasahl.raml.junit.api.factories.TestCaseFactory;
import com.github.claasahl.raml.junit.api.model.Request;
import com.github.claasahl.raml.junit.api.model.RequestConstraints;
import com.github.claasahl.raml.junit.api.model.ResponseConstraints;
import com.github.claasahl.raml.junit.internal.v00.EmptyContraintsFactory;
import com.github.claasahl.raml.junit.internal.v00.EmptyRamlUrlFactory;
import com.github.claasahl.raml.junit.internal.v00.EmptyRequestFactory;
import com.github.claasahl.raml.junit.internal.v00.EmptyTestCaseFactory;

public final class Factories implements TestCaseFactory, ConstraintsFactory, RequestFactory, RamlUrlFactory {

	private static final String TEST_CASE_FACTORY = "raml.junit.test_case_factory.";
	private static final String CONSTRAINTS_FACTORY = "raml.junit.constraint_factory.";
	private static final String REQUEST_FACTORY = "raml.junit.request_factory.";
	private static final String RAML_URL_FACTORY = "raml.junit.raml_url_factory.";
	private static final Map<String, Factories> instances = new HashMap<>();
	private final TestCaseFactory testCaseFactory;
	private final ConstraintsFactory constraintsFactory;
	private final RequestFactory requestFactory;
	private final RamlUrlFactory ramlUrlFactory;

	private Factories(TestCaseFactory testCaseFactory, ConstraintsFactory constraintsFactory,
			RequestFactory requestFactory, RamlUrlFactory ramlUrlFactory) {
		this.testCaseFactory = testCaseFactory;
		this.constraintsFactory = constraintsFactory;
		this.requestFactory = requestFactory;
		this.ramlUrlFactory = ramlUrlFactory;
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

	@Override
	public List<URL> getRamlUrls() {
		return this.ramlUrlFactory.getRamlUrls();
	}

	public static Factories getInstance(String ramlVersion) {
		if (!instances.containsKey(ramlVersion)) {
			instances.put(ramlVersion, createFactory(ramlVersion));
		}
		return instances.get(ramlVersion);
	}

	private static Factories createFactory(String ramlVersion) {
		TestCaseFactory testCaseFactory = createFactory(TEST_CASE_FACTORY + ramlVersion, EmptyTestCaseFactory::new);
		ConstraintsFactory constraintsFactory = createFactory(CONSTRAINTS_FACTORY + ramlVersion,
				EmptyContraintsFactory::new);
		RequestFactory requestFactory = createFactory(REQUEST_FACTORY + ramlVersion, EmptyRequestFactory::new);
		RamlUrlFactory ramlUrlFactory = createFactory(RAML_URL_FACTORY + ramlVersion, EmptyRamlUrlFactory::new);
		return new Factories(testCaseFactory, constraintsFactory, requestFactory, ramlUrlFactory);
	}

	private static <T> T createFactory(String propertyKey, Supplier<T> defaultFactory) {
		String factoryClass = System.getProperty(propertyKey);
		if (factoryClass != null) {
			try {
				@SuppressWarnings("unchecked")
				Class<T> factory = (Class<T>) Class.forName(factoryClass);
				return factory.newInstance();
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
				// TODO write error message to logger
				return null;
			}
		} else {
			return defaultFactory.get();
		}
	}

}
