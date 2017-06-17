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
	public Collection<Parameter> getQueryParameters() {
		return getParameters(method.queryParameters());
	}

	@Override
	public Collection<Parameter> getFormParameters() {
		if (body != null) {
			return getParameters(body.formParameters());
		} else {
			return new ArrayList<>();
		}
	}

	@Override
	public Collection<Parameter> getPathParameters() {
		// TODO append method.baseUriParameters()?
		return getParameters(method.resource().uriParameters());
	}

	@Override
	public Collection<Parameter> getHeaders() {
		return getParameters(method.headers());
	}

	@Override
	public Collection<Parameter> getCookies() {
		return getParameters(new ArrayList<>());
	}

	@Override
	public String getVerb() {
		return method.method();
	}

	@Override
	public String getUrl() {
		return method.resource().resourcePath();
	}

	@Override
	public String getContentType() {
		if (body != null) {
			return body.name();
		} else {
			return null;
		}
	}

	@Override
	public String getBody() {
		if (body != null) {
			if (body.example() != null) {
				return body.example().value();
			} else {
				return "";
			}
		} else {
			return null;
		}
	}

	@Override
	public String toString() {
		return "ResourceRequestV08 [getQueryParameters()=" + getQueryParameters() + ", getFormParameters()="
				+ getFormParameters() + ", getPathParameters()=" + getPathParameters() + ", getHeaders()="
				+ getHeaders() + ", getCookies()=" + getCookies() + ", getVerb()=" + getVerb() + ", getUrl()="
				+ getUrl() + ", getContentType()=" + getContentType() + ", getBody()=" + getBody() + "]";
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
