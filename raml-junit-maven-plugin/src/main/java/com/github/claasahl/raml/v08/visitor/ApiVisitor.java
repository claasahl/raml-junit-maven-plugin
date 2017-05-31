package com.github.claasahl.raml.v08.visitor;

import org.raml.v2.api.model.v08.api.Api;
import org.raml.v2.api.model.v08.api.DocumentationItem;
import org.raml.v2.api.model.v08.api.GlobalSchema;
import org.raml.v2.api.model.v08.methods.Trait;
import org.raml.v2.api.model.v08.parameters.Parameter;
import org.raml.v2.api.model.v08.resources.Resource;
import org.raml.v2.api.model.v08.resources.ResourceType;
import org.raml.v2.api.model.v08.security.SecurityScheme;
import org.raml.v2.api.model.v08.security.SecuritySchemeRef;

import com.github.claasahl.raml.common.visitor.VisitorStrategy;

/**
 * The interface {@link ApiVisitor}.
 * <p>
 * Superclass of all API-based visitors. This class is intended to model a
 * visitor for {@link Api} instances and their (composite) fields.
 * Implementations of this class will most likely extract details (e.g.
 * resources or URI parameters) or otherwise process APIs.
 * <p>
 * This class resembles a derivation of the <i>visitor</i> design pattern. It
 * includes a visit-method for composite fields of the {@link Api} class.
 * 
 * @author Claas Ahlrichs
 * @see VisitorStrategy
 *
 */
public interface ApiVisitor extends VisitorStrategy<Api> {

	/**
	 * Visit method for base URI parameters returned by
	 * {@link Api#baseUriParameters()}.
	 * 
	 * @param baseUriParameter
	 *            a base URI parameter
	 */
	void visitBaseUriParameter(Parameter baseUriParameter);

	/**
	 * Visit method for URI parameters returned by {@link Api#uriParameters()}.
	 * 
	 * @param uriParameter
	 *            a URI parameter
	 */
	void visitUriParameter(Parameter uriParameter);

	/**
	 * Visit method for protocols returned by {@link Api#protocols()}.
	 * 
	 * @param protocol
	 *            a protocol
	 */
	void visitProtocol(String protocol);

	/**
	 * Visit method for schema definitions returned by {@link Api#schemas()}.
	 * 
	 * @param schema
	 *            a schema definition
	 */
	void visitSchema(GlobalSchema schema);

	/**
	 * Visit method for traits returned by {@link Api#traits()}.
	 * 
	 * @param trait
	 *            a trait
	 */
	void visitTrait(Trait trait);

	/**
	 * Visit method for security schemes returned by {@link Api#securedBy()}.
	 * 
	 * @param securedBy
	 *            a security scheme
	 */
	void visitSecuredBy(SecuritySchemeRef securedBy);

	/**
	 * Visit method for security schemes returned by
	 * {@link Api#securitySchemes()}.
	 * 
	 * @param securityScheme
	 *            a security scheme
	 */
	void visitSecurityScheme(SecurityScheme securityScheme);

	/**
	 * Visit method for resource types returned by {@link Api#resourceTypes()}.
	 * 
	 * @param resourceType
	 *            a resource type
	 */
	void visitResourceType(ResourceType resourceType);

	/**
	 * Visit method for resources returned by {@link Api#resources()}.
	 * 
	 * @param resource
	 *            a resource
	 */
	void visitResource(Resource resource);

	/**
	 * Visit method for reference documentations returned by
	 * {@link Api#documentation()}.
	 * 
	 * @param documentationItem
	 *            a reference documentation
	 */
	void visitDocumentation(DocumentationItem documentationItem);

}
