package com.github.claasahl.raml.junit.internal.v08;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.raml.v2.api.model.v08.api.Api;
import org.raml.v2.api.model.v08.bodies.Response;
import org.raml.v2.api.model.v08.methods.Method;
import org.raml.v2.api.model.v08.resources.Resource;

import com.github.claasahl.raml.junit.api.TestCaseKey;
import com.github.claasahl.raml.junit.api.model.Body;
import com.github.claasahl.raml.junit.api.model.Parameter;
import com.github.claasahl.raml.junit.api.model.Request;
import com.github.claasahl.raml.junit.internal.Utils;

public class RequestV08 implements Request {

	private final TestCaseKey key;
	private final Api api;
	private final Resource resource;
	private final Method method;
	private final Response response;

	public RequestV08(TestCaseKey key) {
		this.key = key;
		this.api = Utils.buildApiV08(this.key.getRamlUrl());
		this.resource = resource(this.key.getRequestUrl(), this.api.resources());
		this.method = method(this.key.getRequestVerb(), this.resource.methods());
		this.response = response(this.key.getResponseCode(), this.method.responses());
	}

	@Override
	public Collection<Parameter> getRequestQueryParameters() {
		return parameters(this.method.queryParameters());
	}

	@Override
	public Collection<Parameter> getRequestFormParameters() {
		// TODO ???
		return parameters(new ArrayList<>());
	}

	@Override
	public Collection<Parameter> getRequestPathParameters() {
		return parameters(this.resource.uriParameters());
	}

	@Override
	public Collection<Parameter> getRequestHeaders() {
		return parameters(this.method.headers());
	}

	@Override
	public Collection<Parameter> getRequestCookies() {
		return parameters(new ArrayList<>());
	}

	@Override
	public Body getRequestBody() {
		// TODO Auto-generated method stub
		return null;
	}

	private static Resource resource(String resourcePath, List<Resource> resources) {
		for (Resource resource : resources) {
			if (resourcePath.equals(resource.resourcePath())) {
				return resource;
			} else {
				return resource(resourcePath, resource.resources());
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
	
	private static Collection<Parameter> parameters(List<org.raml.v2.api.model.v08.parameters.Parameter> params) {
		Collection<Parameter> parameters = new ArrayList<>();
		for(org.raml.v2.api.model.v08.parameters.Parameter param : params) {
			String name = param.name();
			String value = param.defaultValue();
			parameters.add(new Parameter(name, value));
		}
		return parameters;
	}

}
