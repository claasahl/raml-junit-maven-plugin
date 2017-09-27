package com.github.claasahl.raml.junit.internal.common;

import static io.restassured.RestAssured.given;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

import com.github.claasahl.raml.junit.api.common.Body;
import com.github.claasahl.raml.junit.api.common.Parameter;
import com.github.claasahl.raml.junit.api.common.Request;
import com.github.claasahl.raml.junit.api.common.ResponseSupplier;

import io.restassured.response.Response;
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
	public RestAssuredResponse getResponse(Request request) {
		Response response = send(request);
		if (response != null) {
			return new RestAssuredResponse(response);
		}
		return null;
	}

	private static Map<String, ?> parameters(Collection<Parameter> parameters) {
		return parameters.stream().collect(Collectors.toMap(Parameter::getName, Parameter::getValues));
	}

	private static Response send(Request request) {
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
			// TODO log error message
		}
		return null;
	}	

}
