package com.github.claasahl.raml.junit;

import org.raml.model.Action;
import org.raml.model.ActionType;
import org.raml.model.Resource;
import org.raml.model.SecurityReference;

import com.github.claasahl.raml.visitor.RamlVisitorFactory;
import com.github.claasahl.raml.visitor.ResourceVisitorBase;

public class RamlTestCasesResourceVisitor extends ResourceVisitorBase {
	private final RamlTestCaseBuilder builder;
	private final RamlVisitorFactory factory;

	public RamlTestCasesResourceVisitor(RamlTestCaseBuilder builder, RamlVisitorFactory factory) {
		super();
		this.builder = builder;
		this.factory = factory;
	}

	@Override
	public void visitResource(Resource resource) {
		System.out.println("RamlTestCasesResourceVisitor.visitResource()");
		this.builder.setResource(resource);
		super.visitResource(resource);
	}

	@Override
	public void visitAction(ActionType actionType, Action action) {
		this.factory.createActionVisitor().visitAction(action);
		super.visitAction(actionType, action);
	}

	@Override
	public void visitSecurityReference(SecurityReference securityReference) {
		this.builder.setSecuredBy(securityReference);
		super.visitSecurityReference(securityReference);
	}

}