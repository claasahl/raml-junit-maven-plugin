package com.github.claasahl.raml.junit.internal;

import com.github.claasahl.raml.junit.api.TestCaseKey;
import com.github.claasahl.raml.junit.api.model.Request;
import com.github.claasahl.raml.junit.api.model.RequestConstraints;
import com.github.claasahl.raml.junit.api.model.ResponseConstraints;

public class TestCase {
	private final TestCaseKey key;
	private final String ramlVersion;
	private RequestConstraints requestConstraints;
	private ResponseConstraints responseConstraints;
	private Request request;

	public TestCase(TestCaseKey key) {
		this.key = key;
		this.ramlVersion = Utils.getRamlVersion(this.key.getRamlUrl());
	}

	public TestCaseKey getKey() {
		return this.key;
	}

	public RequestConstraints getRequestConstraints() {
		if (this.requestConstraints == null) {
			this.requestConstraints = Factories.getInstance(this.ramlVersion).createRequestConstraints(this.key);
		}
		return this.requestConstraints;
	}

	public ResponseConstraints getResponseConstraints() {
		if (this.responseConstraints == null) {
			this.responseConstraints = Factories.getInstance(this.ramlVersion).createResponseConstraints(this.key);
		}
		return responseConstraints;
	}

	public Request getRequest() {
		if (this.request == null) {
			this.request = Factories.getInstance(this.ramlVersion).createRequest(this.key);
		}
		return request;
	}

}
