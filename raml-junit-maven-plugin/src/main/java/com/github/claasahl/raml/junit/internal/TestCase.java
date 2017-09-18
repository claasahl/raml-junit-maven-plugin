package com.github.claasahl.raml.junit.internal;

import com.github.claasahl.raml.junit.api.common.Request;
import com.github.claasahl.raml.junit.api.common.RequestConstraints;
import com.github.claasahl.raml.junit.api.common.Response;
import com.github.claasahl.raml.junit.api.common.ResponseConstraints;
import com.github.claasahl.raml.junit.api.common.TestCaseKey;

public class TestCase {
	private final TestCaseKey key;
	private final String ramlVersion;
	private RequestConstraints requestConstraints;
	private ResponseConstraints responseConstraints;
	private Request request;
	private Response response;
	private boolean performRequest;

	public TestCase(TestCaseKey key) {
		this.key = key;
		this.ramlVersion = Utils.getRamlVersion(this.key.getRamlUrl());
		this.performRequest = true;
	}

	public TestCaseKey getKey() {
		return this.key;
	}

	public RequestConstraints getRequestConstraints() {
		if (this.requestConstraints == null) {
			this.requestConstraints = Factories.getFactories(this.ramlVersion).createRequestConstraints(this.key);
		}
		return this.requestConstraints;
	}

	public ResponseConstraints getResponseConstraints() {
		if (this.responseConstraints == null) {
			this.responseConstraints = Factories.getFactories(this.ramlVersion).createResponseConstraints(this.key);
		}
		return this.responseConstraints;
	}

	public Request getRequest() {
		if (this.request == null) {
			this.request = Factories.getFactories(this.ramlVersion).createRequest(this.key);
		}
		return this.request;
	}

	public Response getResponse() {
		if (this.performRequest) {
			this.performRequest = false;
			this.response = Suppliers.getSuppliers().getResponse(getRequest());
		}
		return this.response;
	}

}
