package com.github.claasahl.raml.visitor;

import java.util.Collection;
import java.util.Map;

import org.raml.model.DocumentationItem;
import org.raml.model.Raml;
import org.raml.model.Resource;
import org.raml.model.SecurityReference;
import org.raml.model.SecurityScheme;
import org.raml.model.Template;
import org.raml.model.parameter.UriParameter;

/**
 * The interface {@link RamlVisitor} provides methods for visiting any
 * {@link Collection} or {@link Map} within {@link Raml} instances.
 * Implementation may be used to "walk" a RAML specification (i.e. "walk" as in
 * "walking" a directory structure).
 * 
 * @author Claas
 *
 */
public interface RamlVisitor {

	void visitRaml(Raml raml);

	void visitBaseUriParameter(String key, UriParameter uriParameter);

	void visitDocumentationItem(DocumentationItem documentationItem);

	void visitResource(String key, Resource resource);

	void visitSchema(Map<String, String> schema);

	void visitSecurityReference(SecurityReference securityReference);

	void visitSecurityScheme(Map<String, SecurityScheme> securityScheme);

	void visitResourceType(Map<String, Template> resourceType);

	void visitTrait(Map<String, Template> trait);

}
