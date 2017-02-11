package com.github.claasahl.raml.visitor;

import java.util.List;
import java.util.Map.Entry;

import org.raml.model.Action;
import org.raml.model.MimeType;
import org.raml.model.Response;
import org.raml.model.SecurityReference;
import org.raml.model.parameter.Header;
import org.raml.model.parameter.QueryParameter;
import org.raml.model.parameter.UriParameter;

public class ActionVisitorBase implements ActionVisitor {

	@Override
	public void visitAction(Action action) {
		visitBaseUriParameters(action);
		visitBodies(action);
		visitHeaders(action);
		visitQueryParameters(action);
		visitSecurityReferences(action);
		visitResponses(action);
	}

	@Override
	public void visitBaseUriParameter(String key, List<UriParameter> uriParameters) {
		// flat
	}

	@Override
	public void visitBody(String key, MimeType mimeType) {
		// FIXME ???
	}

	@Override
	public void visitHeader(String key, Header header) {
		// flat
	}

	@Override
	public void visitQueryParameter(String key, QueryParameter queryParameter) {
		// flat
	}

	@Override
	public void visitSecurityReference(SecurityReference securityReference) {
		// FIXME ???
	}

	@Override
	public void visitResponse(String key, Response response) {
		// TODO Auto-generated method stub
	}
	
	private void visitResponses(Action action) {
		for (Entry<String, Response> entry : action.getResponses().entrySet()) {
			visitResponse(entry.getKey(), entry.getValue());
		}
	}

	private void visitSecurityReferences(Action action) {
		for (SecurityReference securityReference : action.getSecuredBy()) {
			visitSecurityReference(securityReference);
		}
	}

	private void visitQueryParameters(Action action) {
		for (Entry<String, QueryParameter> entry : action.getQueryParameters().entrySet()) {
			visitQueryParameter(entry.getKey(), entry.getValue());
		}
	}

	private void visitHeaders(Action action) {
		for (Entry<String, Header> entry : action.getHeaders().entrySet()) {
			visitHeader(entry.getKey(), entry.getValue());
		}
	}

	private void visitBodies(Action action) {
		for (Entry<String, MimeType> entry : action.getBody().entrySet()) {
			visitBody(entry.getKey(), entry.getValue());
		}
	}

	private void visitBaseUriParameters(Action action) {
		for (Entry<String, List<UriParameter>> entry : action.getBaseUriParameters().entrySet()) {
			visitBaseUriParameter(entry.getKey(), entry.getValue());
		}
	}

}
