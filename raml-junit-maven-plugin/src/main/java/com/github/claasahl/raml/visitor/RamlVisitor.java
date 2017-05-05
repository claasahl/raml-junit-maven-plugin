package com.github.claasahl.raml.visitor;

import java.nio.file.Path;

import org.raml.model.DocumentationItem;
import org.raml.model.Raml;
import org.raml.model.Resource;
import org.raml.model.SecurityReference;
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

	void beforeVisit(Raml raml, Path ramlPath);

	void afterVisit(Raml raml, Path ramlPath);

	void visitBaseUriParameter(String key, UriParameter uriParameter);

	void visitDocumentationItem(DocumentationItem documentationItem);

	void visitResource(String key, Resource resource);

	void visitSecurityReference(SecurityReference securityReference);

}
