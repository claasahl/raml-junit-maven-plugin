package com.github.claasahl.raml.visitor;

import java.util.List;

import org.raml.model.Action;
import org.raml.model.ActionType;
import org.raml.model.Resource;
import org.raml.model.SecurityReference;
import org.raml.model.parameter.UriParameter;

public interface ResourceVisitor {
	
	void visitResource(Resource resource);
	void visitAction(ActionType actionType, Action action);
	void visitBaseUriParameter(String key, List<UriParameter> uriParameters);
	void visitResolvedUriParameter(String key, UriParameter uriParameters);
	void visitSecurityReference(SecurityReference securityReference);
	void visitUriParameter(String key, UriParameter uriParameter);

}
