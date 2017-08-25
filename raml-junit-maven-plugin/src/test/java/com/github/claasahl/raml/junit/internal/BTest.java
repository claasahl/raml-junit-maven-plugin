package com.github.claasahl.raml.junit.internal;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assume.assumeThat;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.github.claasahl.raml.junit.api.model.Body;
import com.github.claasahl.raml.junit.api.model.Parameter;
import com.github.claasahl.raml.junit.api.model.Request;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

@RunWith(Parameterized.class)
public class BTest extends ValidateBase {

	private final TestCase testCase;
	private final Response response;

	public BTest(TestCase testCase) {
		this.testCase = testCase;
		this.response = request(testCase);
	}

	@Test
	public void validateConstraintsForRequestQueryParameters() {
		validateConstraints(true, this.testCase, this.testCase.getRequest().getRequestQueryParameters(),
				this.testCase.getRequestConstraints().getRequestQueryParameters());
	}

	@Test
	public void validateConstraintsForRequestFormParameters() {
		validateConstraints(true, this.testCase, this.testCase.getRequest().getRequestFormParameters(),
				this.testCase.getRequestConstraints().getRequestFormParameters());
	}

	@Test
	public void validateConstraintsForRequestPathParameters() {
		validateConstraints(true, this.testCase, this.testCase.getRequest().getRequestPathParameters(),
				this.testCase.getRequestConstraints().getRequestPathParameters());
	}

	@Test
	public void validateConstraintsForRequestHeaders() {
		validateConstraints(true, this.testCase, this.testCase.getRequest().getRequestHeaders(),
				this.testCase.getRequestConstraints().getRequestHeaders());
	}

	@Test
	public void validateConstraintsForRequestCookies() {
		validateConstraints(true, this.testCase, this.testCase.getRequest().getRequestCookies(),
				this.testCase.getRequestConstraints().getRequestCookies());
	}

	@Test
	public void validateConstraintsForRequestBody() {
		assumeThat(this.testCase.getRequestConstraints().getRequestBody(), notNullValue());
		validateConstraints(true, this.testCase, this.testCase.getRequest().getRequestBody(),
				this.testCase.getRequestConstraints().getRequestBody());
	}

	@Test
	public void validateConstraintsForResponseHeaders() {
		assertThat("No response!", this.response, notNullValue());

		List<Parameter> headers = this.response.getHeaders().asList().stream().map(h -> h.getName()).distinct()
				.map(h -> new Parameter(h, this.response.getHeaders().getValues(h))).collect(Collectors.toList());
		validateConstraints(true, this.testCase, headers, this.testCase.getRequestConstraints().getRequestHeaders());
	}

	@Test
	public void validateConstraintsForResponseCookies() {
		assertThat("No response!", this.response, notNullValue());

		List<Parameter> cookies = StreamSupport.stream(this.response.getDetailedCookies().spliterator(), false)
				.map(c -> c.getName()).map(c -> new Parameter(c, this.response.getDetailedCookies().getValues(c)))
				.collect(Collectors.toList());
		validateConstraints(true, this.testCase, cookies, this.testCase.getRequestConstraints().getRequestCookies());
	}

	@Test
	public void validateConstraintsForResponseBody() {
		assertThat("No response!", this.response, notNullValue());

		assumeThat(this.testCase.getResponseConstraints().getResponseBody(), notNullValue());
		Body body = new Body(this.response.getContentType(), this.response.getBody().asString());
		validateConstraints(true, this.testCase, body, this.testCase.getResponseConstraints().getResponseBody());
	}

	@Parameters
	public static Collection<Object[]> data() {
		return Factories.getRamlUrls().stream().flatMap(u -> {
			String ramlVersion = Utils.getRamlVersion(u);
			return Factories.getFactories(ramlVersion).createTestCases(u).stream();
		}).map(TestCase::new).map(t -> new Object[] { t }).collect(Collectors.toList());
	}

	private static Map<String, ?> parameters(Collection<Parameter> parameters) {
		return parameters.stream().collect(Collectors.toMap(Parameter::getName, Parameter::getValues));
	}

	private static Response request(TestCase testCase) {
		Request request = testCase.getRequest();
		String method = testCase.getKey().getRequestVerb();
		String path = testCase.getKey().getRequestUrl();
		Body body = request.getRequestBody();

		RequestSpecification specification = given().queryParams(parameters(request.getRequestQueryParameters()))
				.formParams(parameters(request.getRequestFormParameters()))
				.pathParams(parameters(request.getRequestPathParameters()))
				.headers(parameters(request.getRequestHeaders())).cookies(parameters(request.getRequestCookies()));
		if (body != null) {
			specification = specification.contentType(body.getContentType()).body(body.getContent());
		}
		return specification.when().request(method, path);
	}

}
