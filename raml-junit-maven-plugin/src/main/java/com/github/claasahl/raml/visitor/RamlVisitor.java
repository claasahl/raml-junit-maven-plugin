package com.github.claasahl.raml.visitor;

import java.util.Map;

import org.raml.model.DocumentationItem;
import org.raml.model.Raml;
import org.raml.model.Resource;
import org.raml.model.SecurityReference;
import org.raml.model.SecurityScheme;
import org.raml.model.Template;
import org.raml.model.parameter.UriParameter;

/**
 * The interface {@link RamlVisitor}.
 * <p>
 * Superclass of all response-based visitors. This class is intended to model a
 * visitor for {@link Raml} instances and their (composite) fields.
 * Implementations of this class will most likely extract details (e.g. URI
 * parameters or resources) or otherwise process responses.
 * <p>
 * This class resembles a derivation of the <i>visitor</i> design pattern. It
 * includes a visit-method for all composite fields of the {@link Raml} class.
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
