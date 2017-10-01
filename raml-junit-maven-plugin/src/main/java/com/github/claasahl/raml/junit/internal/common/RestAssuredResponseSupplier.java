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

	/**
	 * A utility function that converts {@link Parameter}s (internal to this
	 * framework) into parameters usable by rest assured.
	 * 
	 * @param parameters
	 *            the parameters
	 * @return
	 */
	private static Map<String, ?> parameters(Collection<Parameter> parameters) {
		return parameters.stream().collect(Collectors.toMap(Parameter::getName, RestAssuredResponseSupplier::values));
	}

	private static Object values(Parameter parameter) {
		if (parameter == null || parameter.getValues().isEmpty()) {
			return null;
		} else if (parameter.getValues().size() == 1) {
			return parameter.getValues().get(0);
		} else {
			return parameter.getValues();
		}
	}

	/**
	 * A utility function that performs an HTTP request and
	 * 
	 * @param request
	 * @return
	 */
	private static Response send(Request request) {
		try {
			String method = request.getRequestVerb();
			String path = "http://xkcd.com" + request.getRequestUrl();
			Body body = request.getRequestBody();
			RequestSpecification specification = given().log().all()
					.queryParams(parameters(request.getRequestQueryParameters()))
					.formParams(parameters(request.getRequestFormParameters()))
					.pathParams(parameters(request.getRequestPathParameters()))
					.headers(parameters(request.getRequestHeaders())).cookies(parameters(request.getRequestCookies()));
			if (body != null) {
				specification = specification.contentType(body.getContentType()).body(body.getContent());
			}
			return specification.then().log().all().when().request(method, path);
		} catch (Exception e) {
			// TODO log error message
		}
		return null;
	}

}
