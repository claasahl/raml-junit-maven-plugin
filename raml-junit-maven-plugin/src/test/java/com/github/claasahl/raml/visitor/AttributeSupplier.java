package com.github.claasahl.raml.visitor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.raml.model.MimeType;
import org.raml.model.Response;
import org.raml.model.SecurityReference;
import org.raml.model.parameter.Header;
import org.raml.model.parameter.QueryParameter;
import org.raml.model.parameter.UriParameter;

public final class AttributeSupplier {

	public final static Map<String, Header> headers(String... fieldNames) {
		Map<String, Header> headers = new HashMap<>();
		for (String fieldName : fieldNames) {
			headers.put(fieldName, new Header());
		}
		return headers;
	}

	public final static Map<String, MimeType> mimeTypes(String... types) {
		Map<String, MimeType> mimeTypes = new HashMap<>();
		for (String type : types) {
			mimeTypes.put(type, new MimeType(type));
		}
		return mimeTypes;
	}

	public final static Map<String, List<UriParameter>> baseUriParameters(String... names) {
		Map<String, List<UriParameter>> parameters = new HashMap<>();
		for (String name : names) {
			parameters.put(name, Arrays.asList(AttributeSupplier.uriParameter(name)));
		}
		return parameters;
	}

	public final static Map<String, QueryParameter> queryParameters(String... names) {
		Map<String, QueryParameter> parameters = new HashMap<>();
		for (String name : names) {
			parameters.put(name, new QueryParameter());
		}
		return parameters;
	}

	public final static Map<String, Response> responses(String... names) {
		Map<String, Response> responses = new HashMap<>();
		for (String name : names) {
			responses.put(name, new Response());
		}
		return responses;
	}

	public final static List<SecurityReference> securityReference(String... names) {
		List<SecurityReference> references = new ArrayList<>();
		for (String name : names) {
			references.add(new SecurityReference(name));
		}
		return references;
	}

	public final static UriParameter uriParameter(String name) {
		return new UriParameter(name);
	}

}
