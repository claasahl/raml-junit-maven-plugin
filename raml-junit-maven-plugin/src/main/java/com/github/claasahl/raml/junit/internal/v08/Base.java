package com.github.claasahl.raml.junit.internal.v08;

import java.util.List;

import org.raml.v2.api.model.v08.api.Api;
import org.raml.v2.api.model.v08.bodies.BodyLike;
import org.raml.v2.api.model.v08.bodies.Response;
import org.raml.v2.api.model.v08.methods.Method;
import org.raml.v2.api.model.v08.resources.Resource;

import com.github.claasahl.raml.junit.api.common.TestCaseKey;
import com.github.claasahl.raml.junit.internal.Utils;

public class Base {

	private final TestCaseKey key;
	private final Api api;
	private final Resource resource;
	private final Method method;
	private final Response response;
	private final BodyLike requestBody;
	private final BodyLike responseBody;

	protected Base(TestCaseKey key) {
		this.key = key;
		this.api = Utils.buildApiV08(this.key.getRamlUrl());
		this.resource = resource(this.key.getRequestUrl(), this.api.resources());
		this.method = method(this.key.getRequestVerb(), this.resource.methods());
		this.response = response(this.key.getResponseCode(), this.method.responses());
		this.requestBody = body(this.key.getRequestType(), this.method.body());
		this.responseBody = body(this.key.getResponseType(), this.response.body());
	}

	protected TestCaseKey getKey() {
		return key;
	}

	protected Api getApi() {
		return api;
	}

	protected Resource getResource() {
		return resource;
	}

	protected Method getMethod() {
		return method;
	}

	protected Response getResponse() {
		return response;
	}

	protected BodyLike getBody(boolean request) {
		return request ? this.requestBody : this.responseBody;
	}

	private static Resource resource(String resourcePath, List<Resource> resources) {
		for (Resource resource : resources) {
			if (resourcePath.equals(resource.resourcePath())) {
				return resource;
			} else {
				Resource r = resource(resourcePath, resource.resources());
				if(r != null) {
					return r;
				}
			}
		}
		return null;
	}

	private static Method method(String requestVerb, List<Method> methods) {
		for (Method method : methods) {
			if (requestVerb.equals(method.method())) {
				return method;
			}
		}
		return null;
	}

	private static Response response(String responseCode, List<Response> responses) {
		for (Response response : responses) {
			if (responseCode.equals(response.code().value())) {
				return response;
			}
		}
		return null;
	}

	private static BodyLike body(String contentType, List<BodyLike> bodies) {
		for (BodyLike body : bodies) {
			if (body.name().equals(contentType)) {
				return body;
			}
		}
		return null;
	}

}