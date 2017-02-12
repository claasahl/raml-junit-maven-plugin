package com.github.claasahl.raml.visitor;

import java.util.Map.Entry;

import org.raml.model.MimeType;
import org.raml.model.Response;
import org.raml.model.parameter.Header;

public class ResponseVisitorBase implements ResponseVisitor{

	@Override
	public void visitResponse(Response response) {
		visitBodies(response);
		visitHeaders(response);
	}

	@Override
	public void visitBody(String key, MimeType mimeType) {
		// flat
	}

	@Override
	public void visitHeader(String key, Header header) {
		// flat
	}
	
	private void visitHeaders(Response response) {
		for (Entry<String, Header> entry : response.getHeaders().entrySet()) {
			visitHeader(entry.getKey(), entry.getValue());
		}
	}

	private void visitBodies(Response response) {
		for (Entry<String, MimeType> entry : response.getBody().entrySet()) {
			visitBody(entry.getKey(), entry.getValue());
		}
	}

}
