package com.github.claasahl.raml.junit.internal.v08;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.raml.v2.api.model.v08.api.Api;
import org.raml.v2.api.model.v08.bodies.Response;
import org.raml.v2.api.model.v08.methods.Method;
import org.raml.v2.api.model.v08.parameters.Parameter;
import org.raml.v2.api.model.v08.resources.Resource;

import com.github.claasahl.raml.junit.api.TestCaseKey;
import com.github.claasahl.raml.junit.api.model.BodyConstraints;
import com.github.claasahl.raml.junit.api.model.ParameterConstraints;
import com.github.claasahl.raml.junit.api.model.RequestConstraints;
import com.github.claasahl.raml.junit.internal.Utils;

public class RequestConstraintsV08 implements RequestConstraints {

	private final TestCaseKey key;
	private final Api api;
	private final Resource resource;
	private final Method method;
	private final Response response;

	public RequestConstraintsV08(TestCaseKey key) {
		this.key = key;
		this.api = Utils.buildApiV08(this.key.getRamlUrl());
		this.resource = resource(this.key.getRequestUrl(), this.api.resources());
		this.method = method(this.key.getRequestVerb(), this.resource.methods());
		this.response = response(this.key.getResponseCode(), this.method.responses());
	}

	@Override
	public Collection<ParameterConstraints> getRequestQueryParameters() {
		return ParameterConstraintsFactoryV08.createConstraints(this.method.queryParameters());
	}

	@Override
	public Collection<ParameterConstraints> getRequestFormParameters() {
		return ParameterConstraintsFactoryV08.createConstraints(new ArrayList<>());
	}

	@Override
	public Collection<ParameterConstraints> getRequestPathParameters() {
		List<Parameter> parameters = new ArrayList<>();
		parameters.addAll(this.resource.uriParameters());
		parameters.addAll(this.resource.baseUriParameters());
		return ParameterConstraintsFactoryV08.createConstraints(parameters);
	}

	@Override
	public Collection<ParameterConstraints> getRequestHeaders() {
		return ParameterConstraintsFactoryV08.createConstraints(this.method.headers());
	}

	@Override
	public Collection<ParameterConstraints> getRequestCookies() {
		return ParameterConstraintsFactoryV08.createConstraints(new ArrayList<>());
	}

	@Override
	public BodyConstraints getRequestBody() {
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

}
