package com.github.claasahl.raml.junit.internal.v08;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.raml.v2.api.model.v08.bodies.BodyLike;

import com.github.claasahl.raml.junit.api.TestCaseKey;
import com.github.claasahl.raml.junit.api.model.Body;
import com.github.claasahl.raml.junit.api.model.Parameter;
import com.github.claasahl.raml.junit.api.model.Request;

public class RequestV08 extends Base implements Request {

	public RequestV08(TestCaseKey key) {
		super(key);		
	}
	
	@Override
	public String getRequestUrl() {
		return getKey().getRequestUrl();
	}

	@Override
	public String getRequestVerb() {
		return getKey().getRequestVerb();
	}

	@Override
	public Collection<Parameter> getRequestQueryParameters() {
		return parameters(getMethod().queryParameters());
	}

	@Override
	public Collection<Parameter> getRequestFormParameters() {
		// TODO ???
		return parameters(new ArrayList<>());
	}

	@Override
	public Collection<Parameter> getRequestPathParameters() {
		return parameters(getResource().uriParameters());
	}

	@Override
	public Collection<Parameter> getRequestHeaders() {
		return parameters(getMethod().headers());
	}

	@Override
	public Collection<Parameter> getRequestCookies() {
		return parameters(new ArrayList<>());
	}

	@Override
	public Body getRequestBody() {
		BodyLike body = getBody(true);
		return body == null ? null : body(body);
	}

	private static Collection<Parameter> parameters(List<org.raml.v2.api.model.v08.parameters.Parameter> params) {
		Collection<Parameter> parameters = new ArrayList<>();
		for(org.raml.v2.api.model.v08.parameters.Parameter param : params) {
			String name = param.name();
			String value = param.example();
			if(value == null) {
				value = param.defaultValue();
			}
			parameters.add(new Parameter(name, value));
		}
		return parameters;
	}
	
	private static Body body(BodyLike body) {
		String contentType = body.name();
		String content = body.example().toString();
		return new Body(contentType, content);
	}
}
