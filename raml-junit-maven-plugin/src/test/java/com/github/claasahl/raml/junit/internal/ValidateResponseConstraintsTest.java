package com.github.claasahl.raml.junit.internal;

import java.util.Collection;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.github.claasahl.raml.junit.api.common.ParameterConstraints;
import com.github.claasahl.raml.junit.api.common.ResponseConstraints;

@RunWith(Parameterized.class)
public class ValidateResponseConstraintsTest extends ValidateBase {
	private final TestCase testCase;
	private ResponseConstraints constraints;

	public ValidateResponseConstraintsTest(TestCase testCase) {
		this.testCase = testCase;
	}

	@Parameters
	public static Collection<Object[]> data() {
		return Utils.getTestCases().map(t -> new Object[] { t }).collect(Collectors.toList());
	}

	@Before
	public void before() {
		this.constraints = this.testCase.getResponseConstraints();
	}

	@Test
	public void headersMustBeUnique() {
		parametersMustBeUnique(this.constraints.getResponseHeaders(), ParameterConstraints::getName);
	}

	@Test
	public void cookiesMustBeUnique() {
		parametersMustBeUnique(this.constraints.getResponseCookies(), ParameterConstraints::getName);
	}

	@Test
	public void headersMustNotBeNull() {
		parametersMustNotBeNull(this.constraints.getResponseHeaders());
	}

	@Test
	public void cookiesMustNotBeNull() {
		parametersMustNotBeNull(this.constraints.getResponseCookies());
	}

}
