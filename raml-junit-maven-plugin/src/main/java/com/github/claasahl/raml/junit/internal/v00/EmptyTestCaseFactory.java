package com.github.claasahl.raml.junit.internal.v00;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.github.claasahl.raml.junit.api.TestCaseKey;
import com.github.claasahl.raml.junit.api.factories.TestCaseFactory;

/**
 * The class {@link EmptyTestCaseFactory}.
 * <p/>
 * This is an implementation of the {@link TestCaseFactory} which creates no
 * test cases.
 * 
 * @author Claas Ahlrichs
 *
 */
public class EmptyTestCaseFactory implements TestCaseFactory {

	@Override
	public List<TestCaseKey> createTestCases(URL ramlUrl) {
		return new ArrayList<>();
	}

}
