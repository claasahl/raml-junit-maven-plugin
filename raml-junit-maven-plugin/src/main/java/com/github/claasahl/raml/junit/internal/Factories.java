package com.github.claasahl.raml.junit.internal;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import com.github.claasahl.raml.junit.api.RamlUrlSupplier;
import com.github.claasahl.raml.junit.api.TestCaseKey;
import com.github.claasahl.raml.junit.api.factories.ConstraintsFactory;
import com.github.claasahl.raml.junit.api.factories.RequestFactory;
import com.github.claasahl.raml.junit.api.factories.TestCaseFactory;
import com.github.claasahl.raml.junit.api.model.Request;
import com.github.claasahl.raml.junit.api.model.RequestConstraints;
import com.github.claasahl.raml.junit.api.model.ResponseConstraints;
import com.github.claasahl.raml.junit.internal.v00.EmptyContraintsFactory;
import com.github.claasahl.raml.junit.internal.v00.EmptyRamlUrlSupplier;
import com.github.claasahl.raml.junit.internal.v00.EmptyRequestFactory;
import com.github.claasahl.raml.junit.internal.v00.EmptyTestCaseFactory;

public final class Factories implements TestCaseFactory, ConstraintsFactory, RequestFactory {

	private static final String TEST_CASE_FACTORY = "raml.junit.test_case_factory.";
	private static final String CONSTRAINTS_FACTORY = "raml.junit.constraint_factory.";
	private static final String REQUEST_FACTORY = "raml.junit.request_factory.";
	private static final String RAML_URL_SUPPLIER = "raml.junit.raml_url_supplier";
	private static final Map<String, Factories> instances = new HashMap<>();
	private static RamlUrlSupplier ramlUrlSupplier;
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
	
	public static List<URL> getRamlUrls() {
		if(ramlUrlSupplier == null) {
			ramlUrlSupplier = createFactory(RAML_URL_SUPPLIER, EmptyRamlUrlSupplier::new);
		}
		return ramlUrlSupplier.getRamlUrls();
	}

	private static Factories createFactory(String ramlVersion) {
		TestCaseFactory testCaseFactory = createFactory(TEST_CASE_FACTORY + ramlVersion, EmptyTestCaseFactory::new);
		ConstraintsFactory constraintsFactory = createFactory(CONSTRAINTS_FACTORY + ramlVersion,
				EmptyContraintsFactory::new);
		RequestFactory requestFactory = createFactory(REQUEST_FACTORY + ramlVersion, EmptyRequestFactory::new);
		return new Factories(testCaseFactory, constraintsFactory, requestFactory);
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
