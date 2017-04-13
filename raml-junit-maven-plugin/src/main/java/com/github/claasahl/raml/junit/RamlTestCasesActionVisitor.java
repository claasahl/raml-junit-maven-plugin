package com.github.claasahl.raml.junit;

import org.raml.model.Action;
import org.raml.model.MimeType;
import org.raml.model.Response;
import org.raml.model.SecurityReference;

import com.github.claasahl.raml.visitor.ActionVisitorBase;
import com.github.claasahl.raml.visitor.RamlCoordinatorFactory;
import com.github.claasahl.raml.visitor.RamlVisitorFactory;
import com.github.claasahl.raml.visitor.ResponseVisitor;

public class RamlTestCasesActionVisitor extends ActionVisitorBase {
	private final RamlTestCaseBuilder builder;
	private final RamlVisitorFactory visitorFactory;
	private final RamlCoordinatorFactory coordinatorFactory;

	public RamlTestCasesActionVisitor(RamlTestCaseBuilder builder, RamlVisitorFactory visitorFactory, RamlCoordinatorFactory coordinatorFactory) {
		this.builder = builder;
		this.visitorFactory = visitorFactory;
		this.coordinatorFactory = coordinatorFactory;
	}

	@Override
	public void beforeVisit(Action action) {
		this.builder.setAction(action);
	}

	@Override
	public void visitBody(String key, MimeType mimeType) {
		this.builder.setMimeType(mimeType);
	}

	@Override
	public void visitSecurityReference(SecurityReference securityReference) {
		this.builder.setSecuredBy(securityReference);
	}

	@Override
	public void visitResponse(String key, Response response) {
		this.builder.setStatusCode(key);
		
		ResponseVisitor visitor = this.visitorFactory.createResponseVisitor();
		this.coordinatorFactory.visitResponse(response, visitor);
	}

}
