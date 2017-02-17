package com.github.claasahl.raml.visitor.debug;

import java.util.List;

import org.raml.model.Action;
import org.raml.model.ActionType;
import org.raml.model.Resource;
import org.raml.model.SecurityReference;
import org.raml.model.parameter.UriParameter;

import com.github.claasahl.raml.visitor.ResourceVisitorBase;

public class DebugResourceVisitor extends ResourceVisitorBase {

	@Override
	public void visitResource(Resource resource) {
		// TODO Auto-generated method stub
		super.visitResource(resource);
	}

	@Override
	public void visitAction(ActionType actionType, Action action) {
		// TODO Auto-generated method stub
		super.visitAction(actionType, action);
	}

	@Override
	public void visitBaseUriParameter(String key, List<UriParameter> uriParameters) {
		// TODO Auto-generated method stub
		super.visitBaseUriParameter(key, uriParameters);
	}

	@Override
	public void visitResolvedUriParameter(String key, UriParameter uriParameters) {
		// TODO Auto-generated method stub
		super.visitResolvedUriParameter(key, uriParameters);
	}

	@Override
	public void visitSecurityReference(SecurityReference securityReference) {
		// TODO Auto-generated method stub
		super.visitSecurityReference(securityReference);
	}

	@Override
	public void visitUriParameter(String key, UriParameter uriParameter) {
		// TODO Auto-generated method stub
		super.visitUriParameter(key, uriParameter);
	}

}
