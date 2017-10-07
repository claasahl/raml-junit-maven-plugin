package com.github.claasahl.raml.junit.internal;

import java.util.Collection;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.github.claasahl.raml.junit.api.common.ParameterConstraints;
import com.github.claasahl.raml.junit.api.common.RequestConstraints;

/**
 * Ensure that RequestConstraints have "sane" values  
 * @author Claas
 *
 */
@RunWith(Parameterized.class)
public class ValidateRequestConstraintsTest extends ValidateBase {
	private final TestCase testCase;
	private RequestConstraints constraints;

	public ValidateRequestConstraintsTest(TestCase testCase) {
		this.testCase = testCase;
	}

	@Parameters
	public static Collection<Object[]> data() {
		return Utils.getTestCases().map(t -> new Object[] { t }).collect(Collectors.toList());
	}

	@Before
	public void before() {
		this.constraints = this.testCase.getRequestConstraints();
	}

	@Test
	public void queryParametersMustBeUnique() {
		parametersMustBeUnique(this.constraints.getRequestQueryParameters(), ParameterConstraints::getName);
	}

	@Test
	public void formParametersMustBeUnique() {
		parametersMustBeUnique(this.constraints.getRequestFormParameters(), ParameterConstraints::getName);
	}

	@Test
	public void pathParametersMustBeUnique() {
		parametersMustBeUnique(this.constraints.getRequestPathParameters(), ParameterConstraints::getName);
	}

	@Test
	public void headersMustBeUnique() {
		parametersMustBeUnique(this.constraints.getRequestHeaders(), ParameterConstraints::getName);
	}

	@Test
	public void cookiesMustBeUnique() {
		parametersMustBeUnique(this.constraints.getRequestCookies(), ParameterConstraints::getName);
	}

	@Test
	public void queryParametersMustNotBeNull() {
		parametersMustNotBeNull(this.constraints.getRequestQueryParameters());
	}

	@Test
	public void formParametersMustNotBeNull() {
		parametersMustNotBeNull(this.constraints.getRequestFormParameters());
	}

	@Test
	public void pathParametersMustNotBeNull() {
		parametersMustNotBeNull(this.constraints.getRequestPathParameters());
	}

	@Test
	public void headersMustNotBeNull() {
		parametersMustNotBeNull(this.constraints.getRequestHeaders());
	}

	@Test
	public void cookiesMustNotBeNull() {
		parametersMustNotBeNull(this.constraints.getRequestCookies());
	}

}
