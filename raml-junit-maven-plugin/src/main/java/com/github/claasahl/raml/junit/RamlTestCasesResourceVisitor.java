package com.github.claasahl.raml.junit;

import org.raml.model.Action;
import org.raml.model.ActionType;
import org.raml.model.Resource;
import org.raml.model.SecurityReference;

import com.github.claasahl.raml.visitor.ActionVisitor;
import com.github.claasahl.raml.visitor.RamlCoordinatorFactory;
import com.github.claasahl.raml.visitor.RamlVisitorFactory;
import com.github.claasahl.raml.visitor.ResourceVisitor;
import com.github.claasahl.raml.visitor.ResourceVisitorBase;

public class RamlTestCasesResourceVisitor extends ResourceVisitorBase {
	private final RamlTestCaseBuilder builder;
	private final RamlVisitorFactory visitorFactory;
	private final RamlCoordinatorFactory coordinatorFactory;

	public RamlTestCasesResourceVisitor(RamlTestCaseBuilder builder, RamlVisitorFactory visitorFactory,
			RamlCoordinatorFactory coordinatorFactory) {
		this.builder = builder;
		this.visitorFactory = visitorFactory;
		this.coordinatorFactory = coordinatorFactory;
	}

	@Override
	public void beforeVisit(Resource resource) {
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

}
