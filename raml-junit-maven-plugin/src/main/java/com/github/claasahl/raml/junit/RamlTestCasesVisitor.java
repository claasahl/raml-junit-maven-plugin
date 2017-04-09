package com.github.claasahl.raml.junit;

import java.nio.file.Path;
import java.util.Map;

import org.raml.model.DocumentationItem;
import org.raml.model.Raml;
import org.raml.model.Resource;
import org.raml.model.SecurityReference;
import org.raml.model.SecurityScheme;
import org.raml.model.Template;
import org.raml.model.parameter.UriParameter;

import com.github.claasahl.raml.visitor.RamlCoordinatorFactory;
import com.github.claasahl.raml.visitor.RamlVisitor;
import com.github.claasahl.raml.visitor.RamlVisitorFactory;
import com.github.claasahl.raml.visitor.ResourceVisitor;

public class RamlTestCasesVisitor implements RamlVisitor {
	private final RamlTestCaseBuilder builder;
	private final RamlVisitorFactory visitorFactory;
	private final RamlCoordinatorFactory coordinatorFactory;

	public RamlTestCasesVisitor(RamlTestCaseBuilder builder, RamlVisitorFactory visitorFactory, RamlCoordinatorFactory coordinatorFactory) {
		this.builder = builder;
		this.visitorFactory = visitorFactory;
		this.coordinatorFactory = coordinatorFactory;
	}

	@Override
	public void visitRaml(Raml raml, Path ramlPath) {
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

	@Override
	public void visitBaseUriParameter(String key, UriParameter uriParameter) {
		// nothing to be done
	}

	@Override
	public void visitDocumentationItem(DocumentationItem documentationItem) {
		// nothing to be done
	}

	@Override
	public void visitSchema(Map<String, String> schema) {
		// nothing to be done
	}

	@Override
	public void visitSecurityScheme(Map<String, SecurityScheme> securityScheme) {
		// nothing to be done
	}

	@Override
	public void visitResourceType(Map<String, Template> resourceType) {
		// nothing to be done
	}

	@Override
	public void visitTrait(Map<String, Template> trait) {
		// nothing to be done
	}

}
