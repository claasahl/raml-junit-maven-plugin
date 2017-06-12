package com.github.claasahl.raml.v08.junit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApiTestCase {

	// multi value support through List
	private final Map<String, Object> queryParameters = new HashMap<>();
	private final Map<String, Object> formParameters = new HashMap<>();
	private final Map<String, Object> pathParameters = new HashMap<>();
	private final Map<String, Object> cookies = new HashMap<>();
	private final Map<String, Object> headers = new HashMap<>();
	private final String method;
	private final String path;
	private final String contentType;
	private final String body;

	private final Map<String, Object> readOnlyQueryParameters = Collections.unmodifiableMap(queryParameters);
	private final Map<String, Object> readOnlyFormParameters = Collections.unmodifiableMap(formParameters);
	private final Map<String, Object> readOnlyPathParameters = Collections.unmodifiableMap(pathParameters);
	private final Map<String, Object> readOnlyCookies = Collections.unmodifiableMap(cookies);
	private final Map<String, Object> readOnlyHeaders = Collections.unmodifiableMap(headers);

	public ApiTestCase(String method, String path, String contentType, String body) {
		this.method = method;
		this.path = path;
		this.contentType = contentType;
		this.body = body;
	}

	public Map<String, Object> getQueryParameters() {
		return readOnlyQueryParameters;
	}

	public Map<String, Object> getFormParameters() {
		return readOnlyFormParameters;
	}

	public Map<String, Object> getPathParameters() {
		return readOnlyPathParameters;
	}

	public Map<String, Object> getHeaders() {
		return readOnlyHeaders;
	}

	public Map<String, Object> getCookies() {
		return readOnlyCookies;
	}

	public String getMethod() {
		return method;
	}

	public String getPath() {
		return path;
	}

	public String getContentType() {
		return contentType;
	}
	
	public String getBody() {
		return body;
	}
}
