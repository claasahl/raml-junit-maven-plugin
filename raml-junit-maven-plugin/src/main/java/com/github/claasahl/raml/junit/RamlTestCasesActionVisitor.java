package com.github.claasahl.raml.junit;

import java.util.List;

import org.raml.model.Action;
import org.raml.model.MimeType;
import org.raml.model.Response;
import org.raml.model.SecurityReference;
import org.raml.model.parameter.Header;
import org.raml.model.parameter.QueryParameter;
import org.raml.model.parameter.UriParameter;

import com.github.claasahl.raml.visitor.ActionVisitor;
import com.github.claasahl.raml.visitor.RamlCoordinatorFactory;
import com.github.claasahl.raml.visitor.RamlVisitorFactory;
import com.github.claasahl.raml.visitor.ResponseCoordinator;
import com.github.claasahl.raml.visitor.ResponseVisitor;

public class RamlTestCasesActionVisitor implements ActionVisitor {
	private final RamlTestCaseBuilder builder;
	private final RamlVisitorFactory visitorFactory;
	private final RamlCoordinatorFactory coordinatorFactory;

	public RamlTestCasesActionVisitor(RamlTestCaseBuilder builder, RamlVisitorFactory visitorFactory, RamlCoordinatorFactory coordinatorFactory) {
		this.builder = builder;
		this.visitorFactory = visitorFactory;
		this.coordinatorFactory = coordinatorFactory;
	}

	@Override
	public void visitAction(Action action) {
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
		ResponseCoordinator coordinator = this.coordinatorFactory.createResponseCoordinator();
		coordinator.visitResponse(response, visitor);
	}

	@Override
	public void visitBaseUriParameter(String key, List<UriParameter> uriParameters) {
		// nothing to be done
	}

	@Override
	public void visitHeader(String key, Header header) {
		// nothing to be done
	}

	@Override
	public void visitQueryParameter(String key, QueryParameter queryParameter) {
		// nothing to be done
	}
}
