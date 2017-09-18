package com.github.claasahl.raml.junit.internal;

import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.Assert.assertThat;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.github.claasahl.raml.junit.api.common.Parameter;
import com.github.claasahl.raml.junit.api.common.Request;

@RunWith(Parameterized.class)
public class ValidateRequestTest extends ValidateBase {
	private final TestCase testCase;
	private Request request;

	public ValidateRequestTest(TestCase testCase) {
		this.testCase = testCase;
	}

	@Parameters
	public static Collection<Object[]> data() {
		return Utils.getTestCases().map(t -> new Object[] { t }).collect(Collectors.toList());
	}

	@Before
	public void before() {
		this.request = this.testCase.getRequest();
	}

	@Test
	public void queryParametersMustBeUnique() {
		parametersMustBeUnique(this.request.getRequestQueryParameters(), Parameter::getName);
	}

	@Test
	public void formParametersMustBeUnique() {
		parametersMustBeUnique(this.request.getRequestFormParameters(), Parameter::getName);
	}

	@Test
	public void pathParametersMustBeUnique() {
		parametersMustBeUnique(this.request.getRequestPathParameters(), Parameter::getName);
	}

	@Test
	public void headersMustBeUnique() {
		parametersMustBeUnique(this.request.getRequestHeaders(), Parameter::getName);
	}

	@Test
	public void cookiesMustBeUnique() {
		parametersMustBeUnique(this.request.getRequestCookies(), Parameter::getName);
	}

	@Test
	public void queryParametersMustNotBeNull() {
		parametersMustNotBeNull(this.request.getRequestQueryParameters());
	}

	@Test
	public void formParametersMustNotBeNull() {
		parametersMustNotBeNull(this.request.getRequestFormParameters());
	}

	@Test
	public void pathParametersMustNotBeNull() {
		parametersMustNotBeNull(this.request.getRequestPathParameters());
	}

	@Test
	public void headersMustNotBeNull() {
		parametersMustNotBeNull(this.request.getRequestHeaders());
	}

	@Test
	public void cookiesMustNotBeNull() {
		parametersMustNotBeNull(this.request.getRequestCookies());
	}

	@Test
	public void pathParametersMustNotHaveMultipleValues() {
		Collection<Parameter> parameters = this.testCase.getRequest().getRequestPathParameters();
		List<Integer> values = parameters.stream().map(p -> p.getValues().size()).collect(Collectors.toList());
		assertThat(values, everyItem(lessThanOrEqualTo(1)));
	}

}
