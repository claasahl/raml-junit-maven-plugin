package com.github.claasahl.raml.junit;

import java.util.List;

import org.raml.model.Action;
import org.raml.model.ActionType;
import org.raml.model.Resource;
import org.raml.model.SecurityReference;
import org.raml.model.parameter.UriParameter;

import com.github.claasahl.raml.visitor.ActionVisitor;
import com.github.claasahl.raml.visitor.RamlCoordinatorFactory;
import com.github.claasahl.raml.visitor.RamlVisitorFactory;
import com.github.claasahl.raml.visitor.ResourceVisitor;

public class RamlTestCasesResourceVisitor implements ResourceVisitor {
	private final RamlTestCaseBuilder builder;
	private final RamlVisitorFactory visitorFactory;
	private final RamlCoordinatorFactory coordinatorFactory;

	public RamlTestCasesResourceVisitor(RamlTestCaseBuilder builder, RamlVisitorFactory visitorFactory, RamlCoordinatorFactory coordinatorFactory) {
		this.builder = builder;
		this.visitorFactory = visitorFactory;
		this.coordinatorFactory = coordinatorFactory;
	}

	@Override
	public void visitResource(Resource resource) {
		this.builder.setResource(resource);
	}

	@Override
	public void visitAction(ActionType actionType, Action action) {
		ActionVisitor visitor = this.visitorFactory.createActionVisitor();
		this.coordinatorFactory.visitAction(action, visitor);
	}

	@Override
	public void visitSubResource(Resource resource) {
		ResourceVisitor visitor = this.visitorFactory.createResourceVisitor();
		this.coordinatorFactory.visitResource(resource, visitor);
	}
	
	@Override
	public void visitSecurityReference(SecurityReference securityReference) {
		this.builder.setSecuredBy(securityReference);
	}

	@Override
	public void visitBaseUriParameter(String key, List<UriParameter> uriParameters) {
		// nothing to be done
	}

	@Override
	public void visitResolvedUriParameter(String key, UriParameter uriParameters) {
		// nothing to be done
	}

	@Override
	public void visitUriParameter(String key, UriParameter uriParameter) {
		// nothing to be done
	}


}
