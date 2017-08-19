package com.github.claasahl.raml.junit.internal.v08;

import java.util.List;

import org.raml.v2.api.model.v08.api.Api;
import org.raml.v2.api.model.v08.bodies.Response;
import org.raml.v2.api.model.v08.methods.Method;
import org.raml.v2.api.model.v08.resources.Resource;

import com.github.claasahl.raml.junit.api.TestCaseKey;
import com.github.claasahl.raml.junit.internal.Utils;

public class ConstraintsBase {

	private final TestCaseKey key;
	private final Api api;
	private final Resource resource;
	private final Method method;
	private final Response response;

	protected ConstraintsBase(TestCaseKey key) {
		this.key = key;
		this.api = Utils.buildApiV08(this.key.getRamlUrl());
		this.resource = resource(this.key.getRequestUrl(), this.api.resources());
		this.method = method(this.key.getRequestVerb(), this.resource.methods());
		this.response = response(this.key.getResponseCode(), this.method.responses());
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

	protected static Resource resource(String resourcePath, List<Resource> resources) {
		for (Resource resource : resources) {
			if (resourcePath.equals(resource.resourcePath())) {
				return resource;
			} else {
				return resource(resourcePath, resource.resources());
			}
		}
		return null;
	}

	protected static Method method(String requestVerb, List<Method> methods) {
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

}