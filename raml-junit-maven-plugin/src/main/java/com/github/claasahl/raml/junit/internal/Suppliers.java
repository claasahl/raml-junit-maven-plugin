package com.github.claasahl.raml.junit.internal;

import java.net.URL;
import java.util.List;

import com.github.claasahl.raml.junit.api.RamlUrlSupplier;
import com.github.claasahl.raml.junit.api.ResponseSupplier;
import com.github.claasahl.raml.junit.api.model.Request;
import com.github.claasahl.raml.junit.api.model.Response;

public class Suppliers implements RamlUrlSupplier, ResponseSupplier {

	private static final String RAML_URL_SUPPLIER = "raml.junit.raml_url_supplier";
	private static final String RESPONSE_SUPPLIER = "raml.junit.response_supplier";
	private static Suppliers instance;
	private final RamlUrlSupplier ramlUrlSupplier;
	private final ResponseSupplier responseSupplier;

	public Suppliers(RamlUrlSupplier ramlUrlSupplier, ResponseSupplier responseSupplier) {
		this.ramlUrlSupplier = ramlUrlSupplier;
		this.responseSupplier = responseSupplier;
	}

	@Override
	public Response getResponse(Request request) {
		return this.responseSupplier.getResponse(request);
	}

	@Override
	public List<URL> getRamlUrls() {
		return this.ramlUrlSupplier.getRamlUrls();
	}

	public static Suppliers getSuppliers() {
		if (instance == null) {
			RamlUrlSupplier ramlUrlSupplier = Utils.createFactory(RAML_URL_SUPPLIER, null);
			ResponseSupplier responseSupplier = Utils.createFactory(RESPONSE_SUPPLIER, null);
			instance = new Suppliers(ramlUrlSupplier, responseSupplier);
		}
		return instance;
	}
}
