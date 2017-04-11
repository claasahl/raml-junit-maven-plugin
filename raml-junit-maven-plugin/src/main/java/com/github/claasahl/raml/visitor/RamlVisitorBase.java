package com.github.claasahl.raml.visitor;

import java.nio.file.Path;
import java.util.Map;

import org.raml.model.DocumentationItem;
import org.raml.model.Raml;
import org.raml.model.Resource;
import org.raml.model.SecurityReference;
import org.raml.model.SecurityScheme;
import org.raml.model.Template;
import org.raml.model.parameter.UriParameter;

/**
 * The class {@link RamlVisitorBase}.
 * <p>
 * A base implementation of the {@link RamlVisitor} interface. The default
 * implementations do nothing (i.e. empty method bodies).
 * 
 * @author Claas Ahlrichs
 *
 */
public class RamlVisitorBase implements RamlVisitor {

	@Override
	public void beforeVisit(Raml raml, Path ramlPath) {
		// empty body
	}

	@Override
	public void afterVisit(Raml raml, Path ramlPath) {
		// empty body
	}

	@Override
	public void visitBaseUriParameter(String key, UriParameter uriParameter) {
		// empty body
	}

	@Override
	public void visitDocumentationItem(DocumentationItem documentationItem) {
		// empty body
	}

	@Override
	public void visitResource(String key, Resource resource) {
		// empty body
	}

	@Override
	public void visitSchema(Map<String, String> schema) {
		// empty body
	}

	@Override
	public void visitSecurityReference(SecurityReference securityReference) {
		// empty body
	}

	@Override
	public void visitSecurityScheme(Map<String, SecurityScheme> securityScheme) {
		// empty body
	}

	@Override
	public void visitResourceType(Map<String, Template> resourceType) {
		// empty body
	}

	@Override
	public void visitTrait(Map<String, Template> trait) {
		// empty body
	}

}
