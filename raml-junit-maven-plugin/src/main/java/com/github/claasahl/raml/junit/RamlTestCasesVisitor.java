package com.github.claasahl.raml.junit;

import java.nio.file.Path;

import org.raml.model.Raml;
import org.raml.model.Resource;
import org.raml.model.SecurityReference;

import com.github.claasahl.raml.visitor.RamlCoordinatorFactory;
import com.github.claasahl.raml.visitor.RamlVisitorBase;
import com.github.claasahl.raml.visitor.RamlVisitorFactory;
import com.github.claasahl.raml.visitor.ResourceVisitor;

public class RamlTestCasesVisitor extends RamlVisitorBase {
	private final RamlTestCaseBuilder builder;
	private final RamlVisitorFactory visitorFactory;
	private final RamlCoordinatorFactory coordinatorFactory;

	public RamlTestCasesVisitor(RamlTestCaseBuilder builder, RamlVisitorFactory visitorFactory,
			RamlCoordinatorFactory coordinatorFactory) {
		this.builder = builder;
		this.visitorFactory = visitorFactory;
		this.coordinatorFactory = coordinatorFactory;
	}

	@Override
	public void beforeVisit(Raml raml, Path ramlPath) {
		this.builder.setRaml(raml, ramlPath);
	}

	@Override
	public void visitResource(String key, Resource resource) {
		ResourceVisitor visitor = this.visitorFactory.createResourceVisitor();
		this.coordinatorFactory.visitResource(resource, visitor);
	}

	@Override
	public void visitSecurityReference(SecurityReference securityReference) {
		this.builder.setSecuredBy(securityReference);
	}

}
