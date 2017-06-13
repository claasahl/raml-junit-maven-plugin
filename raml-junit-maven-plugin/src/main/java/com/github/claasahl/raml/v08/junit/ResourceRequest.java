package com.github.claasahl.raml.v08.junit;

import java.util.Map;

public interface ResourceRequest {
	Map<String, String> getQueryParameters();

	Map<String, String> getFormParameters();

	Map<String, String> getPathParameters();

	Map<String, String> getHeaders();

	Map<String, String> getCookies();

	String getVerb();

	String getPath();

	String getContentType();

	String getBody();
}
