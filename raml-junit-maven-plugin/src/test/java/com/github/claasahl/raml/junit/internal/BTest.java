package com.github.claasahl.raml.junit.internal;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.Collection;
import java.util.stream.Collectors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class BTest extends ValidateBase {

	private final TestCase testCase;

	public BTest(TestCase testCase) {
		this.testCase = testCase;
	}

	@Test
	public void validateRequestUrl() {
		String reason = String.format("Request URL '%s' for %s failed validation.",
				this.testCase.getRequest().getRequestUrl(), this.testCase.getKey());
		assertThat(reason, this.testCase.getRequest().getRequestUrl(),
				equalTo(this.testCase.getRequestConstraints().getRequestUrl()));
	}

	@Test
	public void validateRequestVerb() {
		String reason = String.format("Request verb '%s' for %s failed validation.",
				this.testCase.getRequest().getRequestVerb(), this.testCase.getKey());
		assertThat(reason, this.testCase.getRequest().getRequestVerb(),
				equalTo(this.testCase.getRequestConstraints().getRequestVerb()));
	}

	@Test
	public void validateConstraintsForRequestQueryParameters() {
		validateConstraints(this.testCase, this.testCase.getRequest().getRequestQueryParameters(),
				this.testCase.getRequestConstraints().getRequestQueryParameters());
	}

	@Test
	public void validateConstraintsForRequestFormParameters() {
		validateConstraints(this.testCase, this.testCase.getRequest().getRequestFormParameters(),
				this.testCase.getRequestConstraints().getRequestFormParameters());
	}

	@Test
	public void validateConstraintsForRequestPathParameters() {
		validateConstraints(this.testCase, this.testCase.getRequest().getRequestPathParameters(),
				this.testCase.getRequestConstraints().getRequestPathParameters());
	}

	@Test
	public void validateConstraintsForRequestHeaders() {
		validateConstraints(this.testCase, this.testCase.getRequest().getRequestHeaders(),
				this.testCase.getRequestConstraints().getRequestHeaders());
	}

	@Test
	public void validateConstraintsForRequestCookies() {
		validateConstraints(this.testCase, this.testCase.getRequest().getRequestCookies(),
				this.testCase.getRequestConstraints().getRequestCookies());
	}

	@Test
	public void validateConstraintsForRequestBody() {
		validateConstraints(this.testCase, this.testCase.getRequest().getRequestBody(),
				this.testCase.getRequestConstraints().getRequestBody());
	}

	@Test
	public void validateResponseCode() {
		String reason = String.format("No response for %s.", this.testCase.getKey());
		assertThat(reason, this.testCase.getResponse(), notNullValue());
		validateConstraints(this.testCase);
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

	@Parameters
	public static Collection<Object[]> data() {
		return Suppliers.getSuppliers().getRamlUrls().stream().flatMap(u -> {
			String ramlVersion = Utils.getRamlVersion(u);
			return Factories.getFactories(ramlVersion).createTestCases(u).stream();
		}).map(TestCase::new).map(t -> new Object[] { t }).collect(Collectors.toList());
	}
}
