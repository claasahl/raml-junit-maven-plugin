package com.github.claasahl.raml.junit.internal;

import java.net.MalformedURLException;

import com.github.claasahl.raml.junit.internal.v08.TestCaseFactoryImpl;

public class TestMain {

	public static void main(String[] args) throws MalformedURLException {
		System.setProperty("raml.junit.test_case_factory.0.8", TestCaseFactoryImpl.class.getName());
		System.setProperty("raml.junit.raml_url_supplier", CommonRamlUrlSupplier.class.getName());

		Suppliers.getSuppliers().getRamlUrls().stream().flatMap(u -> {
			String ramlVersion = Utils.getRamlVersion(u);
			return Factories.getFactories(ramlVersion).createTestCases(u).stream();
		}).forEach(System.out::println);
	}

}
