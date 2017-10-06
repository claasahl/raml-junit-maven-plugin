package com.github.claasahl.raml.junit.internal;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.Collection;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.github.claasahl.raml.junit.api.common.Parameter;
import com.github.claasahl.raml.junit.api.common.Response;

@RunWith(Parameterized.class)
public class ValidateResponseTest extends ValidateBase {
	private final TestCase testCase;
	private Response response;

	public ValidateResponseTest(TestCase testCase) {
		this.testCase = testCase;
	}

	@Parameters
	public static Collection<Object[]> data() {
		return Utils.getTestCases().map(t -> new Object[] { t }).collect(Collectors.toList());
	}

	@Before
	public void before() {
		this.response = this.testCase.getResponse();
	}

	@Test
	public void headersMustBeUnique() {
		parametersMustBeUnique(this.response.getResponseHeaders(), Parameter::getName);
	}

	@Test
	public void cookiesMustBeUnique() {
		parametersMustBeUnique(this.response.getResponseCookies(), Parameter::getName);
	}

	@Test
	public void headersMustNotBeNull() {
		parametersMustNotBeNull(this.response.getResponseHeaders());
	}

	@Test
	public void cookiesMustNotBeNull() {
		parametersMustNotBeNull(this.response.getResponseCookies());
	}
	
	@Test
	public void validateConstraintsForResponseHeaders() {
		String reason = String.format("No response for %s.", this.testCase.getKey());
		assertThat(reason, this.testCase.getResponse(), notNullValue());
		validateConstraints(this.testCase, this.testCase.getResponse().getResponseHeaders(),
				this.testCase.getRequestConstraints().getRequestHeaders());
	}

	@Test
	public void validateConstraintsForResponseCookies() {
		String reason = String.format("No response for %s.", this.testCase.getKey());
		assertThat(reason, this.testCase.getResponse(), notNullValue());
		validateConstraints(this.testCase, this.testCase.getResponse().getResponseCookies(),
				this.testCase.getRequestConstraints().getRequestCookies());
	}

	@Test
	public void validateConstraintsForResponseBody() {
		String reason = String.format("No response for %s.", this.testCase.getKey());
		assertThat(reason, this.testCase.getResponse(), notNullValue());
		validateConstraints(this.testCase, this.testCase.getResponse().getResponseBody(),
				this.testCase.getResponseConstraints().getResponseBody());
	}
	
	@Test
	public void validateResponseCode() {
		String reason = String.format("No response for %s.", this.testCase.getKey());
		assertThat(reason, this.testCase.getResponse(), notNullValue());
		validateConstraints(this.testCase);
	}

}
