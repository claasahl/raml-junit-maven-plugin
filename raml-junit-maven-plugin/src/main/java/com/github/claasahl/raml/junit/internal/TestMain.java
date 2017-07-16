package com.github.claasahl.raml.junit.internal;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import com.github.claasahl.raml.junit.internal.v08.TestCaseFactoryV08;

public class TestMain {

	public static void main(String[] args) throws MalformedURLException {
		System.setProperty("raml.junit.test_case_factory.0.8", TestCaseFactoryV08.class.getName());
		File ramlFile = new File("src/main/raml/v08/licenses2/api.raml");
		URL ramlUrl = ramlFile.toURL();
		String ramlVersion = Utils.getRamlVersion(ramlUrl);
		
		Factories.getInstance(ramlVersion).createTestCases(ramlUrl).stream().forEach(System.out::println);
	}

}
