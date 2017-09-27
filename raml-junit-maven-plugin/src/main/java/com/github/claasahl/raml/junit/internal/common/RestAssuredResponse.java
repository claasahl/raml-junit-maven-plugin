package com.github.claasahl.raml.junit.internal.common;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.github.claasahl.raml.junit.api.common.Body;
import com.github.claasahl.raml.junit.api.common.Parameter;
import com.github.claasahl.raml.junit.api.common.Response;

public class RestAssuredResponse implements Response {

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
