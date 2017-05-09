package com.github.claasahl.raml.visitor;

import java.util.List;

import org.raml.model.Action;
import org.raml.model.ActionType;
import org.raml.model.Resource;
import org.raml.model.SecurityReference;
import org.raml.model.parameter.UriParameter;

/**
 * The class {@link ResourceVisitorBase}.
 * <p>
 * A base implementation of the {@link ResourceVisitor} interface. The default
 * implementations do nothing (i.e. empty method bodies).
 * 
 * @author Claas Ahlrichs
 *
 */
public class ResourceVisitorBase implements ResourceVisitor {

	@Override
	public void beforeVisit(Resource resource) {
		// empty body
	}

	@Override
	public void afterVisit(Resource resource) {
		// empty body
	}

	@Override
	public void visitSubResource(String key, Resource resource) {
		// empty body
	}

	@Override
	public void visitAction(ActionType actionType, Action action) {
		// empty body
	}

	@Override
	public void visitBaseUriParameter(String key, List<UriParameter> uriParameters) {
		// empty body
	}

	@Override
	public void visitResolvedUriParameter(String key, UriParameter uriParameter) {
		// empty body
	}

	@Override
	public void visitSecurityReference(SecurityReference securityReference) {
		// empty body
	}

	@Override
	public void visitUriParameter(String key, UriParameter uriParameter) {
		// empty body
	}

}
