package com.github.claasahl.raml.junit.internal;

import java.net.MalformedURLException;
import java.util.stream.Collectors;

import com.github.claasahl.raml.junit.internal.v08.TestCaseFactoryV08;

public class TestMain {

	public static void main(String[] args) throws MalformedURLException {
		System.setProperty("raml.junit.test_case_factory.0.8", TestCaseFactoryV08.class.getName());
		System.setProperty("raml.junit.raml_url_supplier", CommonRamlUrlSupplier.class.getName());

		Factories.getRamlUrls().stream().flatMap(u -> {
			String ramlVersion = Utils.getRamlVersion(u);
			return Factories.getFactories(ramlVersion).createTestCases(u).stream();
		}).map(TestCase::new).collect(Collectors.toList()).forEach(System.out::println);
	}

}
