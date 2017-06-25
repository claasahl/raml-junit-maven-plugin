package com.github.claasahl.raml.v08.junit;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.raml.v2.api.model.v08.bodies.BodyLike;
import org.raml.v2.api.model.v08.methods.Method;

public class ResourceRequestV08 implements ResourceRequest {

	private final Method method;
	private final BodyLike body;

	public ResourceRequestV08(Method method, BodyLike body) {
		this.method = method;
		this.body = body;
	}

	@Override
	public Collection<Parameter> getRequestQueryParameters() {
		return getParameters(method.queryParameters());
	}

	@Override
	public Collection<Parameter> getRequestFormParameters() {
		if (body != null) {
			return getParameters(body.formParameters());
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
		return getParameters(new ArrayList<>());
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

	@Override
	public String toString() {
		return "ResourceRequestV08 [getQueryParameters()=" + getRequestQueryParameters() + ", getFormParameters()="
				+ getRequestFormParameters() + ", getPathParameters()=" + getRequestPathParameters() + ", getHeaders()="
				+ getRequestHeaders() + ", getCookies()=" + getRequestCookies() + ", getVerb()=" + getRequestVerb()
				+ ", getUrl()=" + getRequestUrl() + ", getBody()=" + getRequestBody() + "]";
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

}
