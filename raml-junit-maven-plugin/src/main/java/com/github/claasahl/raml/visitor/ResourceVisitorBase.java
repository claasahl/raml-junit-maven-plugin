package com.github.claasahl.raml.visitor;

import java.util.List;
import java.util.Map.Entry;

import org.raml.model.Action;
import org.raml.model.ActionType;
import org.raml.model.Resource;
import org.raml.model.SecurityReference;
import org.raml.model.parameter.UriParameter;

public class ResourceVisitorBase implements ResourceVisitor {

	@Override
	public void visitResource(Resource resource) {
		visitActions(resource);
		visitBaseUriParameters(resource);
		visitResolvedUriParameters(resource);
		visitUriParameters(resource);
		visitSecurityReferences(resource);
		visitSubResources(resource);
	}

	@Override
	public void visitAction(ActionType actionType, Action action) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitBaseUriParameter(String key, List<UriParameter> uriParameters) {
		// UriParameter is a "flat" object, there is no need to dive any deeper
	}

	@Override
	public void visitResolvedUriParameter(String key, UriParameter uriParameters) {
		// UriParameter is a "flat" object, there is no need to dive any deeper		
	}

	@Override
	public void visitSecurityReference(SecurityReference securityReference) {
		// SecurityReference is a "flat" object, there is no need to dive any deeper
	}

	@Override
	public void visitUriParameter(String key, UriParameter uriParameter) {
		// UriParameter is a "flat" object, there is no need to dive any deeper		
	}
	
	private void visitSubResources(Resource resource) {
		for (Resource subResource : resource.getResources().values()) {
			visitResource(subResource);
		}
	}

	private void visitSecurityReferences(Resource resource) {
		for (SecurityReference securityReference : resource.getSecuredBy()) {
			visitSecurityReference(securityReference);
		}
	}

	private void visitUriParameters(Resource resource) {
		for (Entry<String, UriParameter> entry : resource.getUriParameters().entrySet()) {
			visitUriParameter(entry.getKey(), entry.getValue());
		}
	}

	private void visitResolvedUriParameters(Resource resource) {
		for (Entry<String, UriParameter> entry : resource.getResolvedUriParameters().entrySet()) {
			visitResolvedUriParameter(entry.getKey(), entry.getValue());
		}
	}

	private void visitBaseUriParameters(Resource resource) {
		for (Entry<String, List<UriParameter>> entry : resource.getBaseUriParameters().entrySet()) {
			visitBaseUriParameter(entry.getKey(), entry.getValue());
		}
	}

	private void visitActions(Resource resource) {
		for (Entry<ActionType, Action> entry : resource.getActions().entrySet()) {
			visitAction(entry.getKey(), entry.getValue());
		}
	}

}
