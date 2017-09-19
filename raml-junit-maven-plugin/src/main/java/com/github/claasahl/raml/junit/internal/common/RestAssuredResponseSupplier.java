package com.github.claasahl.raml.junit.internal.common;

import static io.restassured.RestAssured.given;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.github.claasahl.raml.junit.api.common.Body;
import com.github.claasahl.raml.junit.api.common.Parameter;
import com.github.claasahl.raml.junit.api.common.Request;
import com.github.claasahl.raml.junit.api.common.Response;
import com.github.claasahl.raml.junit.api.common.ResponseSupplier;

import io.restassured.specification.RequestSpecification;

/**
 * The class {@link RestAssuredResponseSupplier}.
 * <p/>
 * This is an implementation of the interface {@link ResponseSupplier} which
 * integrates with the framework rest assured.
 * 
 * @author Claas Ahlrichs
 *
 */
public class RestAssuredResponseSupplier implements ResponseSupplier {

	@Override
	public Response getResponse(Request request) {
		io.restassured.response.Response response = send(request);
		if (response != null) {
			return new RestAssuredResponse(response);
		}
		return null;
	}

	private static Map<String, ?> parameters(Collection<Parameter> parameters) {
		return parameters.stream().collect(Collectors.toMap(Parameter::getName, Parameter::getValues));
	}

	private static io.restassured.response.Response send(Request request) {
		try {
			String method = request.getRequestVerb();
			String path = request.getRequestUrl();
			Body body = request.getRequestBody();
			RequestSpecification specification = given().queryParams(parameters(request.getRequestQueryParameters()))
					.formParams(parameters(request.getRequestFormParameters()))
					.pathParams(parameters(request.getRequestPathParameters()))
					.headers(parameters(request.getRequestHeaders())).cookies(parameters(request.getRequestCookies()));
			if (body != null) {
				specification = specification.contentType(body.getContentType()).body(body.getContent());
			}
			return specification.when().request(method, path);
		} catch (Exception e) {
			// ignore
		}
		return null;
	}

	private static class RestAssuredResponse implements Response {

		private final io.restassured.response.Response response;

		public RestAssuredResponse(io.restassured.response.Response response) {
			this.response = response;
		}

		@Override
		public String getResponseCode() {
			return Integer.toString(this.response.getStatusCode());
		}

		@Override
		public Collection<Parameter> getResponseHeaders() {
			return this.response.getHeaders().asList().stream().map(h -> h.getName()).distinct()
					.map(h -> new Parameter(h, this.response.getHeaders().getValues(h))).collect(Collectors.toList());
		}

		@Override
		public Collection<Parameter> getResponseCookies() {
			return StreamSupport.stream(this.response.getDetailedCookies().spliterator(), false).map(c -> c.getName())
					.map(c -> new Parameter(c, this.response.getDetailedCookies().getValues(c)))
					.collect(Collectors.toList());
		}

		@Override
		public Body getResponseBody() {
			if (this.response.getBody() == null) {
				return null;
			}
			return new Body(response.getContentType(), response.getBody().asString());
		}

	}

}
