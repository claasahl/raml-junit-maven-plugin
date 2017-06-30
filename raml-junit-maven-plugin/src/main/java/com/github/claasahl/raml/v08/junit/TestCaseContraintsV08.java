package com.github.claasahl.raml.v08.junit;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.raml.v2.api.model.v08.bodies.BodyLike;
import org.raml.v2.api.model.v08.bodies.Response;
import org.raml.v2.api.model.v08.methods.Method;

public class TestCaseContraintsV08 implements TestCaseConstraints {

	private final Method method;
	private final BodyLike requestBody;
	private final Response response;
	private final BodyLike responseBody;

	public TestCaseContraintsV08(Method method, BodyLike requestBody, Response response, BodyLike responseBody) {
		this.method = method;
		this.requestBody = requestBody;
		this.response = response;
		this.responseBody = responseBody;
	}

	@Override
	public Collection<Parameter> getRequestQueryParameters() {
		return getParameters(method.queryParameters());
	}

	@Override
	public Collection<Parameter> getRequestFormParameters() {
		if (requestBody != null) {
			return getParameters(requestBody.formParameters());
		} else {
			return new ArrayList<>();
		}
	}

	@Override
	public Collection<Parameter> getRequestPathParameters() {
		// TODO append method.baseUriParameters()?
		return getParameters(method.resource().uriParameters());
	}

	@Override
	public Collection<Parameter> getRequestHeaders() {
		return getParameters(method.headers());
	}

	@Override
	public Collection<Parameter> getRequestCookies() {
		return new ArrayList<>();
	}

	@Override
	public String getRequestVerb() {
		return method.method();
	}

	@Override
	public String getRequestUrl() {
		return method.resource().resourcePath();
	}

	@Override
	public Body getRequestBody() {
		return getBody(requestBody);
	}

	@Override
	public Collection<Parameter> getResponseHeaders() {
		return getParameters(response.headers());
	}

	@Override
	public Collection<Parameter> getResponseCookies() {
		return new ArrayList<>();
	}

	@Override
	public String getResponseStatusCode() {
		return response.code().value();
	}

	@Override
	public Body getResponseBody() {
		return getBody(responseBody);
	}

	@Override
	public String toString() {
		return "TestCaseContraintsV08 [getRequestVerb()=" + getRequestVerb() + ", getRequestUrl()=" + getRequestUrl()
				+ ", getRequestBody().getContentType()="
				+ (getRequestBody() != null ? getRequestBody().getContentType() : null) + ", getResponseStatusCode()="
				+ getResponseStatusCode() + ", getResponseBody().getContentType()="
				+ (getResponseBody() != null ? getResponseBody().getContentType() : null) + "]";
	}

	private static Collection<Parameter> getParameters(List<org.raml.v2.api.model.v08.parameters.Parameter> params) {
		Map<String, Parameter> parameters = new HashMap<>();
		for (org.raml.v2.api.model.v08.parameters.Parameter p : params) {
			String name = p.name();
			String value = null;
			if (p.example() != null) {
				value = p.example();
			} else if (p.defaultValue() != null) {
				value = p.defaultValue();
			}

			if (parameters.containsKey(name)) {
				if (p.repeat() != null && p.repeat().booleanValue()) {
					Parameter parameter = parameters.get(name);
					ArrayList<String> values = new ArrayList<>(parameter.getValues());
					values.add(value);
					parameters.put(name, new Parameter(name, values));
				} else {
					// TODO warning / error: parameter may not have multiple
					// values
				}
			} else if (value != null) {
				parameters.put(name, new Parameter(name, value));
			} else {
				parameters.put(name, new Parameter(name));
			}
		}
		return parameters.values();
	}

	private static Body getBody(BodyLike body) {
		if (body != null) {
			String contentType = body.name();
			if (body.example() != null) {
				return new Body(contentType, body.example().value());
			} else {
				return new Body(contentType, "");
			}
		} else {
			return null;
		}
	}

}
