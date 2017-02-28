package com.github.claasahl.raml.junit;

import org.raml.model.Action;
import org.raml.model.MimeType;
import org.raml.model.Response;
import org.raml.model.SecurityReference;

import com.github.claasahl.raml.visitor.ActionVisitorBase;
import com.github.claasahl.raml.visitor.RamlVisitorFactory;

public class RamlTestCasesActionVisitor extends ActionVisitorBase {
	private final RamlTestCaseBuilder builder;
	private final RamlVisitorFactory factory;

	public RamlTestCasesActionVisitor(RamlTestCaseBuilder builder, RamlVisitorFactory factory) {
		this.builder = builder;
		this.factory = factory;
	}

	@Override
	public void visitAction(Action action) {
		System.out.println("RamlTestCasesActionVisitor.visitAction()");
		this.builder.setAction(action);
		super.visitAction(action);
	}

	@Override
	public void visitBody(String key, MimeType mimeType) {
		this.builder.setMimeType(mimeType);
		super.visitBody(key, mimeType);
	}

	@Override
	public void visitSecurityReference(SecurityReference securityReference) {
		this.builder.setSecuredBy(securityReference);
		super.visitSecurityReference(securityReference);
	}

	@Override
	public void visitResponse(String key, Response response) {
		this.factory.createResponseVisitor().visitResponse(response);
		super.visitResponse(key, response);
	}
}
