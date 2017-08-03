package com.github.claasahl.raml.junit.internal;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assume.assumeThat;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.github.claasahl.raml.junit.api.model.Body;
import com.github.claasahl.raml.junit.api.model.Parameter;
import com.github.claasahl.raml.junit.api.model.Request;

@RunWith(Parameterized.class)
public class BTest extends ValidateBase {

	private final TestCase testCase;

	public BTest(TestCase testCase) {
		this.testCase = testCase;
	}

	@Test
	public void validateConstraintsForRequestQueryParameters() {
		validateConstraints(this.testCase.getRequest().getRequestQueryParameters(),
				this.testCase.getRequestConstraints().getRequestQueryParameters());
	}
	
	@Test
	public void validateConstraintsForRequestFormParameters() {
		validateConstraints(this.testCase.getRequest().getRequestFormParameters(),
				this.testCase.getRequestConstraints().getRequestFormParameters());
	}
	
	@Test
	public void validateConstraintsForRequestPathParameters() {
		validateConstraints(this.testCase.getRequest().getRequestPathParameters(),
				this.testCase.getRequestConstraints().getRequestPathParameters());
	}
	
	@Test
	public void validateConstraintsForRequestHeaders() {
		validateConstraints(this.testCase.getRequest().getRequestHeaders(),
				this.testCase.getRequestConstraints().getRequestHeaders());
	}
	
	@Test
	public void validateConstraintsForRequestCookies() {
		validateConstraints(this.testCase.getRequest().getRequestCookies(),
				this.testCase.getRequestConstraints().getRequestCookies());
	}
	
	// TODO validate headers, cookies from response
	// TODO validate body of request and response
	

	@Test
	public void requestWithoutBody() {
		Request request = this.testCase.getRequest();
		String method = this.testCase.getKey().getRequestVerb();
		String path = this.testCase.getKey().getRequestUrl();
		Body body = request.getRequestBody();
		assumeThat(body, is(nullValue()));

		given().queryParams(parameters(request.getRequestQueryParameters()))
				.formParams(parameters(request.getRequestFormParameters()))
				.pathParams(parameters(request.getRequestPathParameters()))
				.headers(parameters(request.getRequestHeaders())).cookies(parameters(request.getRequestCookies()))
				.when().request(method, path);
	}

	@Test
	public void requestWithBody() {
		Request request = this.testCase.getRequest();
		String method = this.testCase.getKey().getRequestVerb();
		String path = this.testCase.getKey().getRequestUrl();
		Body body = request.getRequestBody();
		assumeThat(body, is(notNullValue()));

		given().queryParams(parameters(request.getRequestQueryParameters()))
				.formParams(parameters(request.getRequestFormParameters()))
				.pathParams(parameters(request.getRequestPathParameters()))
				.headers(parameters(request.getRequestHeaders())).cookies(parameters(request.getRequestCookies()))
				.contentType(body.getContentType()).body(body.getContent()).when().request(method, path);
	}

	@Parameters
	public static Collection<Object[]> data() {
		return Factories.getRamlUrls().stream().flatMap(u -> {
			String ramlVersion = Utils.getRamlVersion(u);
			return Factories.getFactories(ramlVersion).createTestCases(u).stream();
		}).map(TestCase::new).map(t -> new Object[] { t }).collect(Collectors.toList());
	}

	private static Map<String, ?> parameters(Collection<Parameter> parameters) {
		return parameters.stream().collect(Collectors.toMap(Parameter::getName, Function.identity()));
	}

}
