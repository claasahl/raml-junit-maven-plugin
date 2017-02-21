package com.github.claasahl.raml.junit;

import java.nio.file.Path;

import org.raml.model.Raml;
import org.raml.model.Resource;
import org.raml.model.SecurityReference;

import com.github.claasahl.raml.visitor.RamlVisitorBase;
import com.github.claasahl.raml.visitor.RamlVisitorFactory;

public class RamlTestCasesVisitor extends RamlVisitorBase {
	private final RamlTestCaseBuilder builder;
	private final RamlVisitorFactory factory;

	public RamlTestCasesVisitor(RamlTestCaseBuilder builder, RamlVisitorFactory factory) {
		super();
		this.builder = builder;
		this.factory = factory;
	}

	@Override
	public void visitRaml(Raml raml, Path ramlPath) {
		System.out.println("RamlTestCasesVisitor.visitRaml()");
		this.builder.setRaml(raml, ramlPath);
		super.visitRaml(raml, ramlPath);
	}

	@Override
	public void visitResource(String key, Resource resource) {
		this.factory.createResourceVisitor().visitResource(resource);
		super.visitResource(key, resource);
	}

	@Override
	public void visitSecurityReference(SecurityReference securityReference) {
		this.builder.setSecuredBy(securityReference);
		super.visitSecurityReference(securityReference);
	}

}
